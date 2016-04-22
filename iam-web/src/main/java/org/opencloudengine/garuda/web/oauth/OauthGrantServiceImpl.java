package org.opencloudengine.garuda.web.oauth;

import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.*;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Service
public class OauthGrantServiceImpl implements OauthGrantService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OauthTokenService oauthTokenService;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private OauthScopeService oauthScopeService;

    @Override
    public void processTokenInfo(AccessTokenResponse accessTokenResponse) throws Exception {

        Map map = new HashMap();

        //필요한 값을 검증한다.
        String[] params = new String[]{"access_token"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        OauthAccessToken accessToken = oauthTokenService.selectTokenByToken(accessTokenResponse.getAccessToken());
        if (accessToken == null) {
            accessTokenResponse.setError(OauthConstant.INVALID_TOKEN);
            accessTokenResponse.setError_description("Requested access_token is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        OauthClient oauthClient = oauthClientService.selectById(accessToken.getClientId());
        OauthUser oauthUser = oauthUserService.selectById(accessToken.getOauthUserId());

        //코드의 발급시간을 확인한다.
        Timestamp regDate = accessToken.getRegDate();
        Date currentTime = new Date();
        Date expirationTime = new Date(regDate.getTime() + oauthClient.getAccessTokenLifetime() * 1000);
        long diff = (long) Math.floor((expirationTime.getTime() - currentTime.getTime()) / 1000);

        if (diff <= 0) {
            accessTokenResponse.setError(OauthConstant.INVALID_TOKEN);
            accessTokenResponse.setError_description("requested access_token has expired.");
            this.processRedirect(accessTokenResponse);
            return;
        } else {
            map.put("expires_in", diff);
        }

        map.put("client", oauthClient.getClientKey());
        if (oauthUser != null) {
            map.put("username", oauthUser.getUserName());
        }
        if (!StringUtils.isEmpty(accessToken.getRefreshToken())) {
            map.put("refreshToken", accessToken.getRefreshToken());
        }
        map.put("type", accessToken.getType());
        map.put("scope", accessToken.getScopes());
        map.put("additionalInformation", accessToken.getAdditionalInformation());

        String marshal = JsonUtils.marshal(map);
        String prettyPrint = JsonFormatterUtils.prettyPrint(marshal);
        System.out.println(prettyPrint);

        HttpServletResponse response = accessTokenResponse.getResponse();
        response.setStatus(200);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.getWriter().write(prettyPrint);
    }

    @Override
    public void processRefreshToken(AccessTokenResponse accessTokenResponse) throws Exception {
        //필요한 값을 검증한다.
        String[] params = new String[]{"client_id", "client_secret", "grant_type", "refresh_token"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        //클라이언트를 검증한다.
        OauthClient oauthClient = oauthClientService.selectByClientKey(accessTokenResponse.getClientId());
        if (!this.checkClient(oauthClient, accessTokenResponse)) {
            return;
        }
        accessTokenResponse.setOauthClient(oauthClient);

        //매니지먼트를 등록한다.
        Long groupId = oauthClient.getGroupId();
        Management management = managementService.selectById(groupId);
        accessTokenResponse.setManagement(management);

        //클라이언트가 리프레쉬 토큰을 허용하는지 알아본다.
        if(!oauthClient.getRefreshTokenValidity().equals("Y")){
            accessTokenResponse.setError(OauthConstant.UNSUPPORTED_GRANT_TYPE);
            accessTokenResponse.setError_description("Requested client does not support grant_type refresh_token");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //어세스 토큰을 찾는다.
        OauthAccessToken accessToken = oauthTokenService.selectTokenByRefreshToken(accessTokenResponse.getRefreshToken());
        if(accessToken == null){
            accessTokenResponse.setError(OauthConstant.INVALID_TOKEN);
            accessTokenResponse.setError_description("Requested refresh_token is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //어세스 토큰의 발급자 확인
        if(!accessToken.getClientId().equals(oauthClient.getId())){
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("client does not have authority to requested refresh_token.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //어세스 토큰의 발급시간을 확인한다.
        Timestamp regDate = accessToken.getRegDate();
        Date expirationTime = new Date(regDate.getTime() + oauthClient.getRefreshTokenLifetime() * 1000);
        int compareTo = new Date().compareTo(expirationTime);
        if (compareTo > 0) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("requested refresh_token has expired.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //새로운 어세스토큰을 만들고 저장한다.
        OauthAccessToken newAccessToken = new OauthAccessToken();
        newAccessToken.setType("user");
        newAccessToken.setScopes(accessToken.getScopes());
        newAccessToken.setToken(UUID.randomUUID().toString());
        newAccessToken.setOauthUserId(accessToken.getOauthUserId());
        accessToken.setGroupId(management.getId());
        newAccessToken.setClientId(oauthClient.getId());
        newAccessToken.setRefreshToken(UUID.randomUUID().toString());

        oauthTokenService.insertToken(newAccessToken);

        //리스폰스에 리턴값을 세팅한다.
        accessTokenResponse.setTokenType("Bearer");
        accessTokenResponse.setAccessToken(newAccessToken.getToken());
        accessTokenResponse.setRefreshToken(newAccessToken.getRefreshToken());
        accessTokenResponse.setExpiresIn(oauthClient.getAccessTokenLifetime());

        //리스폰스를 수행한다.
        this.processRedirect(accessTokenResponse);
    }

    @Override
    public void processCodeGrant(AccessTokenResponse accessTokenResponse) throws Exception {

        //필요한 값을 검증한다.
        String[] params = new String[]{"client_id", "client_secret", "grant_type", "code"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        //클라이언트를 검증한다.
        OauthClient oauthClient = oauthClientService.selectByClientKey(accessTokenResponse.getClientId());
        if (!this.checkClient(oauthClient, accessTokenResponse)) {
            return;
        }
        accessTokenResponse.setOauthClient(oauthClient);

        //매니지먼트를 등록한다.
        Long groupId = oauthClient.getGroupId();
        Management management = managementService.selectById(groupId);
        accessTokenResponse.setManagement(management);

        //클라이언트의 그런트 타입 허용 범위를 체크한다.
        List grantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().split(","));
        if (!grantTypes.contains("code")) {
            accessTokenResponse.setError(OauthConstant.UNSUPPORTED_GRANT_TYPE);
            accessTokenResponse.setError_description("Requested client does not support grant_type authorization_code");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //리다이렉트 유알엘을 검증한다.
        //요청된 리다이렉트 유알엘 파라미터가 없으면 미리 등재된 유알엘을 등록한다.
        //미리 등재된 유알엘도 없다면 검증을 실패처리한다.
        if (StringUtils.isEmpty(accessTokenResponse.getRedirectUri())) {
            accessTokenResponse.setRedirectUri(oauthClient.getWebServerRedirectUri());
        }
        params = new String[]{"redirect_uri"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }
        //리다이렉트 유알엘이 미리 등재된 유알엘과 같은지 검증한다.
        //리다이렉트 유알엘을 사용자가 파라미터로 등록했을 경우만 해당된다.
        if (!accessTokenResponse.getRedirectUri().equals(oauthClient.getWebServerRedirectUri())) {
            accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
            accessTokenResponse.setError_description("redirect_uri is not match to previously stored redirect_uri.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //코드를 찾는다.
        OauthCode oauthCode = oauthTokenService.selectCodeByCodeAndClientId(accessTokenResponse.getCode(), oauthClient.getId());
        if (oauthCode == null) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("requested code is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }
        //코드의 발급시간을 확인한다.
        Timestamp regDate = oauthCode.getRegDate();
        Date expirationTime = new Date(regDate.getTime() + oauthClient.getCodeLifetime() * 1000);
        int compareTo = new Date().compareTo(expirationTime);
        if (compareTo > 0) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("requested code has expired.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //유저를 등록한다.
        OauthUser oauthUser = oauthUserService.selectById(oauthCode.getOauthUserId());
        if (oauthUser == null) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("requested user is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }
        accessTokenResponse.setOauthUser(oauthUser);


        //어세스토큰을 만든고 저장한다.
        OauthAccessToken accessToken = new OauthAccessToken();
        accessToken.setType("user");
        accessToken.setScopes(oauthCode.getScopes());
        accessToken.setToken(UUID.randomUUID().toString());
        accessToken.setOauthUserId(oauthUser.getId());
        accessToken.setGroupId(management.getId());
        accessToken.setClientId(oauthClient.getId());

        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessToken.setRefreshToken(UUID.randomUUID().toString());
        }
        oauthTokenService.insertToken(accessToken);


        //리스폰스에 리턴값을 세팅한다.
        accessTokenResponse.setTokenType("Bearer");
        accessTokenResponse.setAccessToken(accessToken.getToken());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessTokenResponse.setRefreshToken(accessToken.getRefreshToken());
        }
        accessTokenResponse.setExpiresIn(oauthClient.getAccessTokenLifetime());


        //리스폰스를 수행한다.
        this.processRedirect(accessTokenResponse);
    }

    @Override
    public void processPasswordGrant(AccessTokenResponse accessTokenResponse) throws Exception {
        //필요한 값을 검증한다.
        String[] params = new String[]{"client_id", "client_secret", "grant_type", "username", "password", "scope"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        //클라이언트를 검증한다.
        OauthClient oauthClient = oauthClientService.selectByClientKey(accessTokenResponse.getClientId());
        if (!this.checkClient(oauthClient, accessTokenResponse)) {
            return;
        }
        accessTokenResponse.setOauthClient(oauthClient);

        //매니지먼트를 등록한다.
        Long groupId = oauthClient.getGroupId();
        Management management = managementService.selectById(groupId);
        accessTokenResponse.setManagement(management);

        //클라이언트의 그런트 타입 허용 범위를 체크한다.
        List grantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().split(","));
        if (!grantTypes.contains("password")) {
            accessTokenResponse.setError(OauthConstant.UNSUPPORTED_GRANT_TYPE);
            accessTokenResponse.setError_description("Requested client does not support grant_type password");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //스코프를 검증한다.
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(oauthClient.getId());
        List<OauthScope> requestScopes = new ArrayList<OauthScope>();
        List<String> enabelScopesNames = new ArrayList<>();
        for (int i = 0; i < clientScopes.size(); i++) {
            enabelScopesNames.add(clientScopes.get(i).getName());
        }
        List<String> requestScopesNames = Arrays.asList(accessTokenResponse.getScope().split(","));
        for (int i = 0; i < requestScopesNames.size(); i++) {
            if (!enabelScopesNames.contains(requestScopesNames.get(i))) {
                accessTokenResponse.setError(OauthConstant.INVALID_SCOPE);
                accessTokenResponse.setError_description("Client dost not have requested scope");
                this.processRedirect(accessTokenResponse);
                return;
            } else {
                for (int c = 0; c < clientScopes.size(); c++) {
                    if (clientScopes.get(c).getName().equals(requestScopesNames.get(i))) {
                        requestScopes.add(clientScopes.get(c));
                    }
                }
            }
        }
        accessTokenResponse.setOauthScopes(requestScopes);

        //유저를 찾는다.
        OauthUser oauthUser = oauthUserService.selectByGroupIdAndCredential(management.getId(), accessTokenResponse.getUsername(), accessTokenResponse.getPassword());
        if (oauthUser == null) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("requested user is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }
        accessTokenResponse.setOauthUser(oauthUser);


        //어세스토큰을 만들고 저장한다.
        OauthAccessToken accessToken = new OauthAccessToken();
        accessToken.setType("user");
        accessToken.setScopes(accessTokenResponse.getScope());
        accessToken.setToken(UUID.randomUUID().toString());
        accessToken.setOauthUserId(oauthUser.getId());
        accessToken.setGroupId(management.getId());
        accessToken.setClientId(oauthClient.getId());

        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessToken.setRefreshToken(UUID.randomUUID().toString());
        }
        oauthTokenService.insertToken(accessToken);


        //리스폰스에 리턴값을 세팅한다.
        accessTokenResponse.setTokenType("Bearer");
        accessTokenResponse.setAccessToken(accessToken.getToken());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessTokenResponse.setRefreshToken(accessToken.getRefreshToken());
        }
        accessTokenResponse.setExpiresIn(oauthClient.getAccessTokenLifetime());

        //리스폰스를 수행한다.
        this.processRedirect(accessTokenResponse);
    }

    @Override
    public void processClientCredentialsGrant(AccessTokenResponse accessTokenResponse) throws Exception {
        //필요한 값을 검증한다.
        String[] params = new String[]{"client_id", "client_secret", "grant_type", "scope"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        //클라이언트를 검증한다.
        OauthClient oauthClient = oauthClientService.selectByClientKey(accessTokenResponse.getClientId());
        if (!this.checkClient(oauthClient, accessTokenResponse)) {
            return;
        }
        accessTokenResponse.setOauthClient(oauthClient);

        //매니지먼트를 등록한다.
        Long groupId = oauthClient.getGroupId();
        Management management = managementService.selectById(groupId);
        accessTokenResponse.setManagement(management);

        //클라이언트의 그런트 타입 허용 범위를 체크한다.
        List grantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().split(","));
        if (!grantTypes.contains("credentials")) {
            accessTokenResponse.setError(OauthConstant.UNSUPPORTED_GRANT_TYPE);
            accessTokenResponse.setError_description("Requested client does not support grant_type client_credentials");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //스코프를 검증한다.
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(oauthClient.getId());
        List<OauthScope> requestScopes = new ArrayList<OauthScope>();
        List<String> enabelScopesNames = new ArrayList<>();
        for (int i = 0; i < clientScopes.size(); i++) {
            enabelScopesNames.add(clientScopes.get(i).getName());
        }
        List<String> requestScopesNames = Arrays.asList(accessTokenResponse.getScope().split(","));
        for (int i = 0; i < requestScopesNames.size(); i++) {
            if (!enabelScopesNames.contains(requestScopesNames.get(i))) {
                accessTokenResponse.setError(OauthConstant.INVALID_SCOPE);
                accessTokenResponse.setError_description("Client dost not have requested scope");
                this.processRedirect(accessTokenResponse);
                return;
            } else {
                for (int c = 0; c < clientScopes.size(); c++) {
                    if (clientScopes.get(c).getName().equals(requestScopesNames.get(i))) {
                        requestScopes.add(clientScopes.get(c));
                    }
                }
            }
        }
        accessTokenResponse.setOauthScopes(requestScopes);


        //어세스토큰을 만들고 저장한다.
        OauthAccessToken accessToken = new OauthAccessToken();
        accessToken.setType("client");
        accessToken.setScopes(accessTokenResponse.getScope());
        accessToken.setToken(UUID.randomUUID().toString());
        accessToken.setGroupId(management.getId());
        accessToken.setClientId(oauthClient.getId());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessToken.setRefreshToken(UUID.randomUUID().toString());
        }

        oauthTokenService.insertToken(accessToken);


        //리스폰스에 리턴값을 세팅한다.
        accessTokenResponse.setTokenType("Bearer");
        accessTokenResponse.setAccessToken(accessToken.getToken());
        accessTokenResponse.setExpiresIn(oauthClient.getAccessTokenLifetime());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessTokenResponse.setRefreshToken(accessToken.getRefreshToken());
        }

        //리스폰스를 수행한다.
        this.processRedirect(accessTokenResponse);
    }

    @Override
    public void processJWTGrant(AccessTokenResponse accessTokenResponse) throws Exception {
        //필요한 값을 검증한다.
        String[] params = new String[]{"assertion", "scope"};
        if (!this.checkParameters(params, accessTokenResponse)) {
            return;
        }

        //토큰을 파싱한다.
        String jwtToken = accessTokenResponse.getAssertion();
        JWTClaimsSet jwtClaimsSet = null;
        try {
            jwtClaimsSet = JwtUtils.parseToken(jwtToken);
        } catch (Exception ex) {
            accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
            accessTokenResponse.setError_description("incorrect jwt token format.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        String issuer = jwtClaimsSet.getIssuer();

        //TODO 아래 두 값을 검증하는 방법을 찾아봐야 한다.
        Date issueTime = jwtClaimsSet.getIssueTime();
        Date expirationTime = jwtClaimsSet.getExpirationTime();


        //클라이언트를 찾는다.
        accessTokenResponse.setClientId(issuer);
        OauthClient oauthClient = oauthClientService.selectByClientKey(accessTokenResponse.getClientId());
        if (oauthClient == null) {
            accessTokenResponse.setError(OauthConstant.UNAUTHORIZED_CLIENT);
            accessTokenResponse.setError_description("Requested client is not exist.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //Jwt를 검증한다.
        boolean validatedToken = false;
        validatedToken = JwtUtils.validateToken(jwtToken, oauthClient.getClientJwtSecret());
        if (!validatedToken) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("Invalid jwt signature.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //Jwt 타임아웃 체크
        validatedToken = JwtUtils.validateToken(jwtToken, oauthClient.getClientJwtSecret(), expirationTime);
        if (!validatedToken) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("jwt token is expired.");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //클라이언트 액티브를 체크한다.
        if (!oauthClient.getActiveClient().equals("Y")) {
            accessTokenResponse.setError(OauthConstant.UNAUTHORIZED_CLIENT);
            accessTokenResponse.setError_description("Requested client is not active.");
            this.processRedirect(accessTokenResponse);
            return;
        }
        accessTokenResponse.setOauthClient(oauthClient);

        //매니지먼트를 등록한다.
        Long groupId = oauthClient.getGroupId();
        Management management = managementService.selectById(groupId);
        accessTokenResponse.setManagement(management);

        //클라이언트의 그런트 타입 허용 범위를 체크한다.
        List grantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().split(","));
        if (!grantTypes.contains("credentials")) {
            accessTokenResponse.setError(OauthConstant.UNSUPPORTED_GRANT_TYPE);
            accessTokenResponse.setError_description("Requested client does not support grant_type client_credentials");
            this.processRedirect(accessTokenResponse);
            return;
        }

        //스코프를 검증한다.
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(oauthClient.getId());
        List<OauthScope> requestScopes = new ArrayList<OauthScope>();
        List<String> enabelScopesNames = new ArrayList<>();
        for (int i = 0; i < clientScopes.size(); i++) {
            enabelScopesNames.add(clientScopes.get(i).getName());
        }
        List<String> requestScopesNames = Arrays.asList(accessTokenResponse.getScope().split(","));
        for (int i = 0; i < requestScopesNames.size(); i++) {
            if (!enabelScopesNames.contains(requestScopesNames.get(i))) {
                accessTokenResponse.setError(OauthConstant.INVALID_SCOPE);
                accessTokenResponse.setError_description("Client dost not have requested scope");
                this.processRedirect(accessTokenResponse);
                return;
            } else {
                for (int c = 0; c < clientScopes.size(); c++) {
                    if (clientScopes.get(c).getName().equals(requestScopesNames.get(i))) {
                        requestScopes.add(clientScopes.get(c));
                    }
                }
            }
        }
        accessTokenResponse.setOauthScopes(requestScopes);


        //어세스토큰을 만들고 저장한다.
        OauthAccessToken accessToken = new OauthAccessToken();
        accessToken.setType("client");
        accessToken.setScopes(accessTokenResponse.getScope());
        accessToken.setToken(UUID.randomUUID().toString());
        accessToken.setGroupId(management.getId());
        accessToken.setClientId(oauthClient.getId());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessToken.setRefreshToken(UUID.randomUUID().toString());
        }

        oauthTokenService.insertToken(accessToken);


        //리스폰스에 리턴값을 세팅한다.
        accessTokenResponse.setTokenType("Bearer");
        accessTokenResponse.setAccessToken(accessToken.getToken());
        accessTokenResponse.setExpiresIn(oauthClient.getAccessTokenLifetime());
        if (oauthClient.getRefreshTokenValidity().equals("Y")) {
            accessTokenResponse.setRefreshToken(accessToken.getRefreshToken());
        }

        //리스폰스를 수행한다.
        this.processRedirect(accessTokenResponse);
    }

    @Override
    public void processRedirect(AccessTokenResponse accessTokenResponse) {
        try {
            if (accessTokenResponse.getError() != null) {
                Map map = new HashMap();
                map.put("error", accessTokenResponse.getError());
                map.put("error_description", accessTokenResponse.getError_description());

                String marshal = JsonUtils.marshal(map);
                String prettyPrint = JsonFormatterUtils.prettyPrint(marshal);
                System.out.println(prettyPrint);

                HttpServletResponse response = accessTokenResponse.getResponse();

                response.setStatus(400);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Pragma", "no-cache");
                response.getWriter().write(prettyPrint);
            } else {
                Map map = new HashMap();

                switch (accessTokenResponse.getGrant_type()) {
                    case "authorization_code":
                        map.put("access_token", accessTokenResponse.getAccessToken());
                        map.put("token_type", accessTokenResponse.getTokenType());
                        map.put("expires_in", accessTokenResponse.getExpiresIn());
                        if (!StringUtils.isEmpty(accessTokenResponse.getRefreshToken())) {
                            map.put("refresh_token", accessTokenResponse.getRefreshToken());
                        }
                        break;

                    case "password":
                        map.put("access_token", accessTokenResponse.getAccessToken());
                        map.put("token_type", accessTokenResponse.getTokenType());
                        map.put("expires_in", accessTokenResponse.getExpiresIn());
                        if (!StringUtils.isEmpty(accessTokenResponse.getRefreshToken())) {
                            map.put("refresh_token", accessTokenResponse.getRefreshToken());
                        }
                        break;

                    case "client_credentials":
                        map.put("access_token", accessTokenResponse.getAccessToken());
                        map.put("token_type", accessTokenResponse.getTokenType());
                        map.put("expires_in", accessTokenResponse.getExpiresIn());
                        if (!StringUtils.isEmpty(accessTokenResponse.getRefreshToken())) {
                            map.put("refresh_token", accessTokenResponse.getRefreshToken());
                        }
                        break;

                    case "urn:ietf:params:oauth:grant-type:jwt-bearer":
                        map.put("access_token", accessTokenResponse.getAccessToken());
                        map.put("token_type", accessTokenResponse.getTokenType());
                        map.put("expires_in", accessTokenResponse.getExpiresIn());
                        if (!StringUtils.isEmpty(accessTokenResponse.getRefreshToken())) {
                            map.put("refresh_token", accessTokenResponse.getRefreshToken());
                        }
                        break;

                    case "refresh_token":
                        map.put("access_token", accessTokenResponse.getAccessToken());
                        map.put("token_type", accessTokenResponse.getTokenType());
                        map.put("expires_in", accessTokenResponse.getExpiresIn());
                        map.put("refresh_token", accessTokenResponse.getRefreshToken());
                        break;
                }

                String marshal = JsonUtils.marshal(map);
                String prettyPrint = JsonFormatterUtils.prettyPrint(marshal);
                System.out.println(prettyPrint);

                HttpServletResponse response = accessTokenResponse.getResponse();

                response.setStatus(200);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Pragma", "no-cache");
                response.getWriter().write(prettyPrint);
            }
        } catch (IOException ex) {
            //response 전달 과정 중 실패가 일어나더라도 프로세스에는 영향을 끼지지 않는다.
        }
    }

    private boolean checkParameters(String[] params, AccessTokenResponse accessTokenResponse) {
        List<String> list = Arrays.asList(params);
        for (int i = 0; i < list.size(); i++) {
            String paramName = list.get(i);
            switch (paramName) {
                case "client_id":
                    if (StringUtils.isEmpty(accessTokenResponse.getClientId())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("client_id is required.");
                    }
                    break;
                case "client_secret":
                    if (StringUtils.isEmpty(accessTokenResponse.getClientSecret())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("client_secret is required.");
                    }
                    break;
                case "grant_type":
                    if (StringUtils.isEmpty(accessTokenResponse.getGrant_type())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("grant_type is required.");
                    }
                    break;
                case "code":
                    if (StringUtils.isEmpty(accessTokenResponse.getCode())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("code is required.");
                    }
                    break;
                case "username":
                    if (StringUtils.isEmpty(accessTokenResponse.getUsername())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("username is required.");
                    }
                    break;
                case "password":
                    if (StringUtils.isEmpty(accessTokenResponse.getPassword())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("password is required.");
                    }
                    break;
                case "scope":
                    if (StringUtils.isEmpty(accessTokenResponse.getScope())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("scope is required.");
                    }
                    break;
                case "redirect_uri":
                    if (StringUtils.isEmpty(accessTokenResponse.getRedirectUri())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("Requested client does not have default redirect_uri. You must set redirect_uri in your parameters.");
                    }
                    break;
                case "assertion":
                    if (StringUtils.isEmpty(accessTokenResponse.getAssertion())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("assertion is required.");
                    }
                    break;
                case "access_token":
                    if (StringUtils.isEmpty(accessTokenResponse.getAccessToken())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("access_token is required.");
                    }
                    break;
                case "refresh_token":
                    if (StringUtils.isEmpty(accessTokenResponse.getRefreshToken())) {
                        accessTokenResponse.setError(OauthConstant.INVALID_REQUEST);
                        accessTokenResponse.setError_description("refresh_token is required.");
                    }
                    break;
            }
            if (accessTokenResponse.getError() != null) {
                this.processRedirect(accessTokenResponse);
                return false;
            }
        }
        return true;
    }

    private boolean checkClient(OauthClient oauthClient, AccessTokenResponse accessTokenResponse) {
        if (oauthClient == null) {
            accessTokenResponse.setError(OauthConstant.UNAUTHORIZED_CLIENT);
            accessTokenResponse.setError_description("Requested client is not exist.");
            this.processRedirect(accessTokenResponse);
            return false;
        }

        //클라이언트 액티브를 체크한다.
        if (!oauthClient.getActiveClient().equals("Y")) {
            accessTokenResponse.setError(OauthConstant.UNAUTHORIZED_CLIENT);
            accessTokenResponse.setError_description("Requested client is not active.");
            this.processRedirect(accessTokenResponse);
            return false;
        }

        //클라이언트 비밀번호를 체크한다.
        if (!oauthClient.getClientSecret().equals(accessTokenResponse.getClientSecret())) {
            accessTokenResponse.setError(OauthConstant.ACCESS_DENIED);
            accessTokenResponse.setError_description("client_secret is not match.");
            this.processRedirect(accessTokenResponse);
            return false;
        }

        return true;
    }
}
