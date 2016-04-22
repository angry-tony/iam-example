package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientRepository {
    String NAMESPACE = OauthClientRepository.class.getName();

    int insert(OauthClient OauthClient);

    OauthClient selectById(Long id);

    List<OauthClient> selectByGroupId(Long groupId);

    OauthClient selectByGroupIdAndName(Long groupId, String name);

    OauthClient selectByGroupIdAndId(Long groupId, Long id);

    OauthClient selectByClientKey(String clientKey);

    OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret);

    List<OauthClient> selectByCondition(OauthClient oauthClient);

    int updateById(OauthClient OauthClient);

    int deleteById(Long id);
}
