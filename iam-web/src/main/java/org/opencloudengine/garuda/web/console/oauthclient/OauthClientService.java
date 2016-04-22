package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientService {

    OauthClient selectById(Long id);

    List<OauthClient> selectByGroupId(Long groupId);

    OauthClient selectByGroupIdAndName(Long groupId, String name);

    OauthClient selectByGroupIdAndId(Long groupId, Long id);

    OauthClient selectByClientKey(String clientKey);

    OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret);

    List<OauthClient> selectByCondition(OauthClient oauthClient);

    int updateById(Long id, String name, String description, String clientTrust, String clientType, String activeClient, String authorizedGrantTypes,
                   String webServerRedirectUri, String refreshTokenValidity, String additionalInformation, Integer codeLifetime,
                   Integer refreshTokenLifetime, Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes);

    int updateById(OauthClient oauthClient);

    int deleteById(Long id);

    OauthClient createClient(Long groupId, String name, String description, String clientTrust, String clientType, String activeClient, String authorizedGrantTypes,
                     String webServerRedirectUri, String refreshTokenValidity, String additionalInformation, Integer codeLifetime,
                     Integer refreshTokenLifetime, Integer accessTokenLifetime, Integer jwtTokenLifetime, String scopes);
}
