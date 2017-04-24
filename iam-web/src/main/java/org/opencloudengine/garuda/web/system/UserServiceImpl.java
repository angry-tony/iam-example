/**
 * Copyright (C) 2011 Flamingo Project (http://www.opencloudengine.org).
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opencloudengine.garuda.web.system;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.mail.MailService;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.util.JwtUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthuser.OauthSessionToken;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.registe.Registe;
import org.opencloudengine.garuda.web.registe.RegisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Seungpil PARK
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private RegisteRepository registeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Autowired
    private MailService mailService;

    @Override
    public void acknowledge(String email) {
        userRepository.updateByAck(email);
    }

    @Override
    public User createUser(User user) {
        String email = user.getEmail();

        if (userRepository.selectByUserEmail(email) != null) {
            throw new ServiceException("이미 존재하는 사용자입니다.");
        }
        user.setAuthority("ROLE_USER");
        return userRepository.insertByUser(user);
    }

    @Override
    public User updatePassword(String email, String password) {
        return userRepository.updatePassword(email, password);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User updateUserInfo(User user) {
        return userRepository.updateUserInfo(user);
    }

    @Override
    public User selectByUserId(String id) {
        return userRepository.selectByUserId(id);
    }

    @Override
    public User selectByUserEmail(String email) {
        return userRepository.selectByUserEmail(email);
    }

    @Override
    public boolean waitingConfirmation(String email) {
        User user = userRepository.selectByUserEmail(email);
        if (user != null) {
            if (!user.getEnabled())
                return true;
        }
        return false;
    }

    @Override
    public boolean completeAccount(String email) {
        User user = userRepository.selectByUserEmail(email);
        if (user != null) {
            if (user.getEnabled())
                return true;
        }
        return false;
    }

    @Override
    public void sendPasswdMail(String email) {
        User user = userRepository.selectByUserEmail(email);
        Registe registe = new Registe();
        registe.setUserId(user.get_id());

        String fromUser = config.getProperty("mail.contacts.address");
        String token = new String(Base64.encode(String.valueOf(System.currentTimeMillis()).getBytes()));
        registe.setToken(token);

        registeRepository.insert(registe);
        mailService.passwd(registe.get_id(), token, "Forgot Password", fromUser, "uEngine", email, null);
    }

    @Override
    public boolean reqPasswdExist(String user_id, String token) {
        Registe registe = new Registe();
        registe.setUserId(user_id);
        registe.setToken(token);
        Registe managedRegiste = registeRepository.selectByUserIdAndToken(registe);
        if (managedRegiste == null)
            return false;
        return true;

    }

    @Override
    public String generateSessionToken(String username, String password) {

        try {
            //발급 시간
            Date issueTime = new Date();

            //만료시간
            Date expirationTime = new Date(new Date().getTime() +
                    Integer.parseInt(configurationHelper.get("security.web.session.timeout")) * 1000);

            //발급자
            String issuer = configurationHelper.get("security.jwt.issuer");

            //시그네이쳐 설정
            String sharedSecret = configurationHelper.get("security.jwt.secret");
            JWSSigner signer = new MACSigner(sharedSecret);

            //콘텍스트 설정
            Map context = new HashMap();
            context.put("username", username);
            context.put("password", password);

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            JWTClaimsSet claimsSet = builder
                    .issuer(issuer)
                    .issueTime(issueTime)
                    .expirationTime(expirationTime)
                    .claim("context", context).build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);

            String sessionToken = signedJWT.serialize();

            return sessionToken;
        } catch (Exception ex) {
            throw new ServiceException("Failed to generate session token", ex);
        }
    }

    @Override
    public User validateSessionToken(String sessionToken) {

        try {
            JWTClaimsSet jwtClaimsSet = JwtUtils.parseToken(sessionToken);

            String sharedSecret = configurationHelper.get("security.jwt.secret");
            Map context = (Map) jwtClaimsSet.getClaim("context");
            String username = (String) context.get("username");
            String password = (String) context.get("password");

            //만료시간
            Date issueTime = jwtClaimsSet.getIssueTime();
            Date expirationTime = new Date(issueTime.getTime() + Integer.parseInt(configurationHelper.get("security.web.session.timeout")) * 1000);

            boolean validated = JwtUtils.validateToken(sessionToken, expirationTime);
            if (validated) {
                User user = this.selectByUserEmail(username);
                if (user != null && password.equals(user.getPassword())) {
                    return user;
                } else {
                    throw new ServiceException("Failed to validate session token");
                }
            } else {
                throw new ServiceException("Failed to validate session token");
            }
        } catch (Exception ex) {
            throw new ServiceException("Failed to validate session token", ex);
        }
    }
}
