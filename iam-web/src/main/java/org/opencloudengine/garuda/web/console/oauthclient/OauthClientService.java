package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientService {

    OauthClient selectById(Long id);

    List<OauthClient> selectByGroupId(Long groupId);

    OauthClient selectByGroupIdAndName(Long groupId, String name);

    OauthClient selectByGroupIdAndId(Long groupId, Long id);

    int updateById(Long id, String name, String description, String clientTrust, String clientType, boolean activeClient, String authorizedGrantTypes,
                   String webServerRedirectUri, boolean refreshTokenValidity, String additionalInformation, int codeLifetime,
                   int refreshTokenLifetime, int accessTokenLifetime, int jwtTokenLifetime, String scopes);

    int deleteById(Long id);

    int createClient(Long groupId, String name, String description, String clientTrust, String clientType, boolean activeClient, String authorizedGrantTypes,
                     String webServerRedirectUri, boolean refreshTokenValidity, String additionalInformation, int codeLifetime,
                     int refreshTokenLifetime, int accessTokenLifetime, int jwtTokenLifetime, String scopes);

    int insertScopes(OauthClientScopes oauthClientScopes);

    List<OauthClientScopes> selectScopes(Long clientId);

    int deleteScopes(Long clientId);
}
