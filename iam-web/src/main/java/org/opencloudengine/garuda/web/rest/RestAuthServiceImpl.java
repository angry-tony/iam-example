package org.opencloudengine.garuda.web.rest;

import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.opencloudengine.garuda.web.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class RestAuthServiceImpl implements RestAuthService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private UserService userService;

    @Override
    public User userParser(HttpServletRequest request) {
        Map<String, String> headers = this.getHeaders(request);

        String sessionToken = headers.get("authorization");

        if (!StringUtils.isEmpty(sessionToken)) {
            try {
                return userService.validateSessionToken(sessionToken);
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public OauthClient clientParser(HttpServletRequest request) {

        Map<String, String> headers = this.getHeaders(request);
        String clientKey = headers.get("client-key");
        String clientSecret = headers.get("client-secret");


        //클라이언트 키와 시크릿 헤더 파악
        if (!StringUtils.isEmpty(clientKey) && !StringUtils.isEmpty(clientSecret)) {
            OauthClient oauthClient = oauthClientService.selectByClientKeyAndSecret(clientKey, clientSecret);
            if (oauthClient == null) {
                return null;
            }
            if (!"trust".equals(oauthClient.getClientTrust())) {
                return null;
            }
            return oauthClient;
        } else {
            return null;
        }
    }

    @Override
    public Management managementParser(HttpServletRequest request) {

        Map<String, String> headers = this.getHeaders(request);
        String managementKey = headers.get("management-key");
        String managementSecret = headers.get("management-secret");

        String clientKey = headers.get("client-key");
        String clientSecret = headers.get("client-secret");


        //매니지먼트 키와 시크릿으로 헤더 파악
        if (!StringUtils.isEmpty(managementKey) && !StringUtils.isEmpty(managementSecret)) {
            return managementService.selectByCredential(managementKey, managementSecret);
        }
        //클라이언트 키와 시크릿 헤더 파악
        else if (!StringUtils.isEmpty(clientKey) && !StringUtils.isEmpty(clientSecret)) {

            OauthClient oauthClient = oauthClientService.selectByClientKeyAndSecret(clientKey, clientSecret);
            if (oauthClient == null) {
                return null;
            }
            if (!oauthClient.getClientTrust().equals("trust")) {
                return null;
            }
            return managementService.selectById(oauthClient.getManagementId());

        } else {
            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> list = Collections.list(headerNames);
        Map<String, String> map = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            map.put(name.toLowerCase(), request.getHeader(name));
        }
        return map;
    }
}
