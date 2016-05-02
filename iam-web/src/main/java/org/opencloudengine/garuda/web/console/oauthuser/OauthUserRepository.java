package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.web.management.Management;

import java.util.List;

public interface OauthUserRepository {
    String NAMESPACE = OauthUserRepository.class.getName();

    OauthUser insert(OauthUser oauthUser);

    OauthUser selectById(String id);

    List<OauthUser> selectByManagementId(String managementId);

    OauthUser selectByManagementIdAndUserName(String managementId, String userName);

    OauthUser selectByManagementIdAndCredential(String managementId, String userName, String userPassword);

    OauthUser selectByManagementIdAndId(String managementId, String id);

    OauthUser updateById(OauthUser oauthUser);

    void deleteById(String id);
}
