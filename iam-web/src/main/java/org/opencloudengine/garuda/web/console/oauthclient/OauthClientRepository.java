package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;

import java.util.List;

public interface OauthClientRepository {
    String NAMESPACE = OauthClientRepository.class.getName();

    int insert(OauthUser oauthUser);

    OauthUser selectById(Long id);

    List<OauthUser> selectByGroupId(Long groupId);

    OauthUser selectByGroupIdAndUserName(Long groupId, String userName);

    OauthUser selectByGroupIdAndId(Long groupId, Long id);

    int updateById(OauthUser oauthUser);

    int deleteById(Long id);
}
