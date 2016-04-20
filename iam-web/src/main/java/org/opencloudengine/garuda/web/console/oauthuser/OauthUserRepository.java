package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.web.management.Management;

import java.util.List;

public interface OauthUserRepository {
    String NAMESPACE = OauthUserRepository.class.getName();

    int insert(OauthUser oauthUser);

    OauthUser selectById(Long id);

    List<OauthUser> selectByGroupId(Long groupId);

    OauthUser selectByGroupIdAndUserName(Long groupId, String userName);

    OauthUser selectByGroupIdAndCredential(Long groupId, String userName, String userPassword);

    OauthUser selectByGroupIdAndId(Long groupId, Long id);

    int updateById(OauthUser oauthUser);

    int deleteById(Long id);
}
