package org.opencloudengine.garuda.web.console.oauthregiste;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.commons.codec.binary.*;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.mail.MailService;
import org.opencloudengine.garuda.util.DateUtils;
import org.opencloudengine.garuda.util.JwtUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthuser.*;
import org.opencloudengine.garuda.web.console.template.TemplateRepository;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.opencloudengine.garuda.web.notification.NotificationConfigRepository;
import org.opencloudengine.garuda.web.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.*;

@Service
public class OauthRegisteServiceImpl implements OauthRegisteService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    OauthRegisteRepository registeRepository;

    @Autowired
    MailService mailService;

    @Autowired
    OauthUserService oauthUserService;

    /**
     * 회원 가입 요청시 이메일을 발송하고 토큰을 저장한다.
     *
     * @param oauthClient
     * @param oauthUser
     * @param redirect_url
     * @return
     * @throws Exception
     */
    @Override
    public OauthRegiste singUp(OauthClient oauthClient, OauthUser oauthUser, String redirect_url) throws Exception {
        String token = new String(Base64.encode(String.valueOf(System.currentTimeMillis()).getBytes()));

        OauthRegiste oauthRegiste = new OauthRegiste();
        oauthRegiste.setRedirect_url(redirect_url);
        oauthRegiste.setClientId(oauthClient.get_id());
        oauthRegiste.setNotification_type(NotificationType.SIGN_UP.toString());
        oauthRegiste.setOauthUser(oauthUser);
        oauthRegiste.setToken(token);

        mailService.notificationMail(oauthClient, oauthUser, NotificationType.SIGN_UP, redirect_url, token);
        registeRepository.insert(oauthRegiste);

        return oauthRegiste;
    }

    @Override
    public OauthRegiste forgotPassword(OauthClient oauthClient, OauthUser oauthUser, String redirect_url) throws Exception {
        String token = new String(Base64.encode(String.valueOf(System.currentTimeMillis()).getBytes()));

        OauthRegiste oauthRegiste = new OauthRegiste();
        oauthRegiste.setRedirect_url(redirect_url);
        oauthRegiste.setClientId(oauthClient.get_id());
        oauthRegiste.setNotification_type(NotificationType.FORGOT_PASSWORD.toString());
        oauthRegiste.setOauthUser(oauthUser);
        oauthRegiste.setToken(token);

        mailService.notificationMail(oauthClient, oauthUser, NotificationType.FORGOT_PASSWORD, redirect_url, token);
        registeRepository.insert(oauthRegiste);

        return oauthRegiste;
    }

    /**
     * 사인업 또는 비밀번호 찾기 요청의 토큰을 검증한다.
     *
     * @param oauthClient
     * @param token
     * @param notification_type
     * @return
     * @throws Exception
     */
    @Override
    public OauthUser verification(OauthClient oauthClient, String token, String notification_type) throws Exception {

        //토큰 만료 시간이 지났을 경우.
        long tokenTimestamp = Long.parseLong(new String(org.apache.commons.codec.binary.Base64.decodeBase64(token)));
        if (DateUtils.getDiffDays(new Date(), new Date(tokenTimestamp)) > 1) {
            return null;
        }

        OauthRegiste oauthRegiste = registeRepository.selectByClientIdAndTokenAndType(oauthClient.get_id(), token, notification_type);
        if (oauthRegiste == null) {
            return null;
        }

        //비밀번호 요청인 경우 현재 사용자 정보를 리턴한다.
        if (NotificationType.FORGOT_PASSWORD.toString().equals(notification_type)) {
            return oauthUserService.selectById(oauthRegiste.getOauthUser().get_id());
        }
        //가입 요청인 경우 저장된 사용자 정보를 리턴한다.
        else {
            return oauthRegiste.getOauthUser();
        }
    }


    @Override
    public OauthUser acceptSingUp(OauthClient oauthClient, String token) throws Exception {

        //토큰 만료 시간이 지났을 경우.
        long tokenTimestamp = Long.parseLong(new String(org.apache.commons.codec.binary.Base64.decodeBase64(token)));
        if (DateUtils.getDiffDays(new Date(), new Date(tokenTimestamp)) > 1) {
            return null;
        }

        OauthRegiste oauthRegiste = registeRepository.selectByClientIdAndTokenAndType(oauthClient.get_id(), token, NotificationType.SIGN_UP.toString());
        if (oauthRegiste == null) {
            return null;
        }

        //사용자를 등록한다.
        OauthUser oauthUser = oauthRegiste.getOauthUser();
        oauthUser = oauthUserService.createUser(oauthClient.getManagementId(), oauthUser);

        //사용자 가입확인 메일 발송.
        mailService.notificationMail(oauthClient, oauthUser, NotificationType.SIGNED_UP, null, null);

        //레지스트 삭제
        registeRepository.deleteById(oauthRegiste.get_id());

        return oauthUser;
    }

    @Override
    public OauthUser acceptPassword(OauthClient oauthClient, String token, String password) throws Exception {
        //토큰 만료 시간이 지났을 경우.
        long tokenTimestamp = Long.parseLong(new String(org.apache.commons.codec.binary.Base64.decodeBase64(token)));
        if (DateUtils.getDiffDays(new Date(), new Date(tokenTimestamp)) > 1) {
            return null;
        }

        OauthRegiste oauthRegiste = registeRepository.selectByClientIdAndTokenAndType(oauthClient.get_id(), token, NotificationType.FORGOT_PASSWORD.toString());
        if (oauthRegiste == null) {
            return null;
        }

        //사용자를 패스워드를 변경한다.
        OauthUser oauthUser = oauthUserService.selectById(oauthRegiste.getOauthUser().get_id());
        oauthUser.setUserPassword(password);
        oauthUserService.updateById(oauthUser);
        oauthUser = oauthUserService.selectById(oauthUser.get_id());

        //사용자 패스워드 변경 확인 메일 발송.
        mailService.notificationMail(oauthClient, oauthUser, NotificationType.PASSWORD_CHANGED, null, null);

        //레지스트 삭제
        registeRepository.deleteById(oauthRegiste.get_id());

        return oauthUser;
    }

    @Override
    public OauthUser rePassword(OauthClient oauthClient, OauthUser oauthUser, String beforePassword, String afterPassword) throws Exception {

        //패스워드가 맞지 않다면 에러.
        if (!oauthUser.getUserPassword().equals(beforePassword)) {
            throw new SecurityException("beforePassword is not match to afterPassword for user " + oauthUser.getUserName());
        }

        //사용자를 패스워드를 변경한다.
        oauthUser.setUserPassword(afterPassword);
        oauthUserService.updateById(oauthUser);
        oauthUser = oauthUserService.selectById(oauthUser.get_id());

        //사용자 패스워드 변경 확인 메일 발송.
        mailService.notificationMail(oauthClient, oauthUser, NotificationType.PASSWORD_CHANGED, null, null);

        return oauthUser;
    }

    @Override
    public OauthRegiste selectByClientIdAndTokenAndType(String clientId, String token, String notification_type) {
        return registeRepository.selectByClientIdAndTokenAndType(clientId,token,notification_type);
    }

    @Override
    public OauthRegiste selectById(String id) {
        return registeRepository.selectById(id);
    }
}
