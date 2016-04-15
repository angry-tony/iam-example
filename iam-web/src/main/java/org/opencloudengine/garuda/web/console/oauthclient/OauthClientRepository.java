package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientRepository {
    String NAMESPACE = OauthClientRepository.class.getName();

    int insert(OauthClient OauthClient);

    OauthClient selectById(Long id);

    List<OauthClient> selectByGroupId(Long groupId);

    OauthClient selectByGroupIdAndName(Long groupId, String name);

    OauthClient selectByGroupIdAndId(Long groupId, Long id);

    int updateById(OauthClient OauthClient);

    int deleteById(Long id);

    int insertScopes(OauthClientScopes oauthClientScopes);

    List<OauthClientScopes> selectScopes(Long clientId);

    int deleteScopes(Long clientId);
}
