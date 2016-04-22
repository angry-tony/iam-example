package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class OauthClientServiceImpl implements OauthClientService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Autowired
    private OauthScopeService oauthScopeService;

    @Autowired
    ConfigurationHelper configurationHelper;

    private String TRUST_CLIENT_TYPE = "trust";

    @Override
    public OauthClient selectById(Long id) {
        return oauthClientRepository.selectById(id);
    }

    @Override
    public List<OauthClient> selectByGroupId(Long groupId) {
        return oauthClientRepository.selectByGroupId(groupId);
    }

    @Override
    public OauthClient selectByGroupIdAndName(Long groupId, String name) {
        return oauthClientRepository.selectByGroupIdAndName(groupId, name);
    }

    @Override
    public OauthClient selectByGroupIdAndId(Long groupId, Long id) {
        return oauthClientRepository.selectByGroupIdAndId(groupId, id);
    }

    @Override
    public OauthClient selectByClientKey(String clientKey) {
        return oauthClientRepository.selectByClientKey(clientKey);
    }

    @Override
    public OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret) {
        return oauthClientRepository.selectByClientKeyAndSecret(clientKey, clientSecret);
    }

    @Override
    public List<OauthClient> selectByCondition(OauthClient oauthClient) {
        return oauthClientRepository.selectByCondition(oauthClient);
    }

    @Override
    public int updateById(Long id, String name, String description, String clientTrust, String clientType, String activeClient,
                          String authorizedGrantTypes, String webServerRedirectUri, String refreshTokenValidity,
                          String additionalInformation, Integer codeLifetime, Integer refreshTokenLifetime,
                          Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes) {
        OauthClient oauthClient = new OauthClient();
        oauthClient.setId(id);
        oauthClient.setName(name);
        oauthClient.setDescription(description);
        oauthClient.setClientTrust(clientTrust);
        oauthClient.setClientType(clientType);
        oauthClient.setActiveClient(activeClient);
        oauthClient.setAuthorizedGrantTypes(authorizedGrantTypes);
        oauthClient.setWebServerRedirectUri(webServerRedirectUri);
        oauthClient.setRefreshTokenValidity(refreshTokenValidity);
        oauthClient.setAdditionalInformation(additionalInformation);
        oauthClient.setCodeLifetime(codeLifetime);
        oauthClient.setRefreshTokenLifetime(refreshTokenLifetime);
        oauthClient.setAccessTokenLifetime(accessTokenLifetime);
        oauthClient.setJwtTokenLifetime(jwtTokenLifetime);

        int update = oauthClientRepository.updateById(oauthClient);

        //스코프 처리
        oauthScopeService.deleteClientScopes(oauthClient.getId());
        if (!StringUtils.isEmpty(scopes)) {
            String[] split = scopes.split(",");
            for (int i = 0; i < split.length; i++) {
                String scope = split[i];
                OauthClientScopes oauthClientScopes = new OauthClientScopes();
                oauthClientScopes.setClientId(oauthClient.getId());
                oauthClientScopes.setScopeId(Long.parseLong(scope));
                oauthScopeService.insertClientScopes(oauthClientScopes);
            }
        }
        return update;
    }

    @Override
    public int updateById(OauthClient oauthClient) {
        return oauthClientRepository.updateById(oauthClient);
    }

    @Override
    public OauthClient createClient(Long groupId, String name, String description, String clientTrust, String clientType, String activeClient,
                                    String authorizedGrantTypes, String webServerRedirectUri, String refreshTokenValidity,
                                    String additionalInformation, Integer codeLifetime, Integer refreshTokenLifetime,
                                    Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes) {

        OauthClient oauthClient = new OauthClient();
        oauthClient.setGroupId(groupId);

        oauthClient.setClientKey(UUID.randomUUID().toString());
        oauthClient.setClientSecret(UUID.randomUUID().toString());
        oauthClient.setClientJwtSecret(UUID.randomUUID().toString());

        oauthClient.setName(name);
        oauthClient.setDescription(description);
        oauthClient.setClientTrust(clientTrust);
        oauthClient.setClientType(clientType);
        oauthClient.setActiveClient(activeClient);
        oauthClient.setAuthorizedGrantTypes(authorizedGrantTypes);
        oauthClient.setWebServerRedirectUri(webServerRedirectUri);
        oauthClient.setRefreshTokenValidity(refreshTokenValidity);
        oauthClient.setAdditionalInformation(additionalInformation);
        oauthClient.setCodeLifetime(codeLifetime);
        oauthClient.setRefreshTokenLifetime(refreshTokenLifetime);
        oauthClient.setAccessTokenLifetime(accessTokenLifetime);
        oauthClient.setJwtTokenLifetime(jwtTokenLifetime);
        oauthClientRepository.insert(oauthClient);

        //스코프 처리
        if (!StringUtils.isEmpty(scopes)) {
            String[] split = scopes.split(",");
            for (int i = 0; i < split.length; i++) {
                String scope = split[i];
                OauthClientScopes oauthClientScopes = new OauthClientScopes();
                oauthClientScopes.setClientId(oauthClient.getId());
                oauthClientScopes.setScopeId(Long.parseLong(scope));
                oauthScopeService.insertClientScopes(oauthClientScopes);
            }
        }

        return oauthClient;
    }

    @Override
    public int deleteById(Long id) {
        return oauthClientRepository.deleteById(id);
    }
}
