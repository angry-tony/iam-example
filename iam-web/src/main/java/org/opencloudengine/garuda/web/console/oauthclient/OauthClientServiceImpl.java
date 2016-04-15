package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.management.Management;
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
    private ManagementService managementService;

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
    public int updateById(Long id, String name, String description, String clientTrust, String clientType, boolean activeClient,
                          String authorizedGrantTypes, String webServerRedirectUri, boolean refreshTokenValidity,
                          String additionalInformation, int codeLifetime, int refreshTokenLifetime,
                          int accessTokenLifetime, int jwtTokenLifetime, String scopes) {
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
        oauthClientRepository.deleteScopes(oauthClient.getId());
        if (!StringUtils.isEmpty(scopes)) {
            String[] split = scopes.split(",");
            for (int i = 0; i < split.length; i++) {
                String scope = split[i];
                OauthClientScopes oauthClientScopes = new OauthClientScopes();
                oauthClientScopes.setClientId(oauthClient.getId());
                oauthClientScopes.setScopeId(Long.parseLong(scope));
                oauthClientRepository.insertScopes(oauthClientScopes);
            }
        }
        return update;
    }

    @Override
    public int createClient(Long groupId, String name, String description, String clientTrust, String clientType, boolean activeClient,
                            String authorizedGrantTypes, String webServerRedirectUri, boolean refreshTokenValidity,
                            String additionalInformation, int codeLifetime, int refreshTokenLifetime,
                            int accessTokenLifetime, int jwtTokenLifetime, String scopes) {

        OauthClient oauthClient = new OauthClient();
        oauthClient.setGroupId(groupId);

        //신뢰 어플리케이션
        if (clientType.equals(this.TRUST_CLIENT_TYPE)) {
            Management management = managementService.selectById(groupId);
            oauthClient.setClientKey(management.getGroupKey());
            oauthClient.setClientSecret(management.getGroupSecret());
            oauthClient.setClientJwtSecret(management.getGroupJwtSecret());
        }
        //서드 파티 어플리케이션
        else {
            oauthClient.setClientKey(UUID.randomUUID().toString());
            oauthClient.setClientSecret(UUID.randomUUID().toString());
            oauthClient.setClientJwtSecret(UUID.randomUUID().toString());
        }

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
        int insert = oauthClientRepository.insert(oauthClient);

        //스코프 처리
        if (!StringUtils.isEmpty(scopes)) {
            String[] split = scopes.split(",");
            for (int i = 0; i < split.length; i++) {
                String scope = split[i];
                OauthClientScopes oauthClientScopes = new OauthClientScopes();
                oauthClientScopes.setClientId(oauthClient.getId());
                oauthClientScopes.setScopeId(Long.parseLong(scope));
                oauthClientRepository.insertScopes(oauthClientScopes);
            }
        }

        return insert;
    }

    @Override
    public int deleteById(Long id) {
        return oauthClientRepository.deleteById(id);
    }

    @Override
    public int insertScopes(OauthClientScopes oauthClientScopes) {
        return oauthClientRepository.insertScopes(oauthClientScopes);
    }

    @Override
    public List<OauthClientScopes> selectScopes(Long clientId) {
        return oauthClientRepository.selectScopes(clientId);
    }

    @Override
    public int deleteScopes(Long clientId) {
        return oauthClientRepository.deleteScopes(clientId);
    }
}
