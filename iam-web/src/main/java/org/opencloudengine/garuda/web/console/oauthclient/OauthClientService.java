package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientService {

    OauthClient selectById(String id);

    List<OauthClient> selectByManagementId(String managementId);

    OauthClient selectByManagementIdAndName(String managementId, String name);

    OauthClient selectByManagementIdAndId(String managementId, String id);

    OauthClient selectByClientKey(String clientKey);

    OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret);

    OauthClient updateById(String id, String name, String description, String clientTrust, String clientType, String activeClient, String authorizedGrantTypes,
                           String webServerRedirectUri, String refreshTokenValidity, Integer codeLifetime,
                           Integer refreshTokenLifetime, Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes);

    OauthClient updateById(OauthClient oauthClient);

    void deleteById(String id);

    OauthClient createClient(String managementId, String name, String description, String clientTrust, String clientType, String activeClient, String authorizedGrantTypes,
                             String webServerRedirectUri, String refreshTokenValidity, Integer codeLifetime,
                             Integer refreshTokenLifetime, Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes);
}
