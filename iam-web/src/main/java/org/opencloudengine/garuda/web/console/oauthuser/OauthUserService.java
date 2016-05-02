package org.opencloudengine.garuda.web.console.oauthuser;

import java.util.List;

public interface OauthUserService {

    OauthUser selectById(String id);

    List<OauthUser> selectByManagementId(String managementId);

    OauthUser selectByManagementIdAndUserName(String managementId, String userName);

    OauthUser selectByManagementIdAndCredential(String managementId, String userName, String userPassword);

    OauthUser selectByManagementIdAndId(String managementId, String id);

    OauthUser updateById(String id, String userName, String userPassword, Integer level);

    OauthUser updateById(OauthUser oauthUser);

    void deleteById(String id);

    OauthUser createUser(String managementId, OauthUser oauthUser);

    OauthSessionToken validateSessionToken(String sessionToken) throws Exception;

    OauthScopeToken validateScopeToken(String scopeToken) throws Exception;

    OauthSessionToken generateSessionToken(String managementKey, String userName, String userPassword) throws Exception;

    OauthScopeToken generateScopeToken(String managementKey, String userName, String clientKey, String scopes) throws Exception;
}
