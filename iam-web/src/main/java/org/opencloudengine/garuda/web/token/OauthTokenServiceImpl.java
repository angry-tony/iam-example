package org.opencloudengine.garuda.web.token;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.JwtUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthTokenRepository oauthTokenRepository;

    @Override
    public OauthCode insertCode(OauthCode oauthCode) {
        return oauthTokenRepository.insertCode(oauthCode);
    }

    @Override
    public OauthCode selectCodeById(String id) {
        return oauthTokenRepository.selectCodeById(id);
    }

    @Override
    public OauthCode selectCodeByCode(String code) {
        return oauthTokenRepository.selectCodeByCode(code);
    }

    @Override
    public OauthCode selectCodeByCodeAndClientId(String code, String clientId) {
        return oauthTokenRepository.selectCodeByCodeAndClientId(code, clientId);
    }

    @Override
    public void deleteCodeById(String id) {
        oauthTokenRepository.deleteCodeById(id);
    }

    @Override
    public OauthAccessToken insertToken(OauthAccessToken oauthAccessToken) {
        oauthTokenRepository.insertToken(oauthAccessToken);
        return oauthAccessToken;
    }

    @Override
    public OauthAccessToken selectTokenById(String id) {
        return oauthTokenRepository.selectTokenById(id);
    }

    @Override
    public OauthAccessToken selectTokenByToken(String token) {
        return oauthTokenRepository.selectTokenByToken(token);
    }

    @Override
    public OauthAccessToken selectTokenByOldRefreshToken(String refreshToken) {
        return oauthTokenRepository.selectTokenByOldRefreshToken(refreshToken);
    }

    @Override
    public OauthAccessToken selectTokenByRefreshToken(String refreshToken) {
        return oauthTokenRepository.selectTokenByRefreshToken(refreshToken);
    }

    @Override
    public OauthAccessToken selectTokenByManagementIdAndId(String managementId, String id) {
        return oauthTokenRepository.selectTokenByManagementIdAndId(managementId, id);
    }

    @Override
    public OauthAccessToken updateTokenById(OauthAccessToken oauthAccessToken) {
        return oauthTokenRepository.updateTokenById(oauthAccessToken);
    }

    @Override
    public void deleteTokenById(String id) {
        oauthTokenRepository.deleteTokenById(id);
    }

    @Override
    public String generateJWTToken(OauthUser oauthUser, OauthClient oauthClient, OauthAccessToken accessToken, String claimJson, Integer lifetime, String type) throws Exception {

        //발급 시간
        Date issueTime = new Date();

        //만료시간
        Date expirationTime = new Date(new Date().getTime() + lifetime * 1000);

        //발급자
        String issuer = config.getProperty("security.jwt.issuer");

        //콘텍스트 설정
        Map context = new HashMap();
        context.put("managementId", accessToken.getManagementId());
        context.put("clientId", accessToken.getClientId());
        context.put("clientKey", oauthClient.getClientKey());
        context.put("type", accessToken.getType());
        context.put("scopes", accessToken.getScopes());
        context.put("refreshToken", accessToken.getRefreshToken());

        if (type.equals("user")) {
            context.put("userId", accessToken.getOauthUserId());
            context.put("userName", oauthUser.getUserName());
            Map<String, Object> userMap = JsonUtils.convertClassToMap(oauthUser);

            //remove unused fields.
            userMap.remove("userPassword");
            userMap.remove("_rev");
            userMap.remove("docType");

            context.put("user", userMap);
        }

        //클라이언트의 콘텍스트 필수 항목만 context 에 집어넣는다.
        String requiredContext = oauthClient.getRequiredContext() != null ? oauthClient.getRequiredContext() : "";
        List<String> contextList = Arrays.asList(requiredContext.split(","));
        Object[] keyArray = context.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            if (!contextList.contains(keyArray[i])) {
                context.remove(keyArray[i]);
            }
        }

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        JWTClaimsSet claimsSet = builder
                .issuer(issuer)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .claim("context", context)
                .claim("claim", StringUtils.isEmpty(claimJson) ? new HashMap<>() : JsonUtils.marshal(claimJson))
                .build();

        //알고리즘 판별.
        String algorithm = oauthClient.getJwtAlgorithm();

        if (JWSAlgorithm.RS256.getName().equals(algorithm)) {
            JWSSigner signer = new RSASSASigner(JwtUtils.getRSAPrivateKey());
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        }
        //디폴트는 HS256
        else {
            JWSSigner signer = new MACSigner(JwtUtils.getHS256SecretKey());
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        }
    }
}
