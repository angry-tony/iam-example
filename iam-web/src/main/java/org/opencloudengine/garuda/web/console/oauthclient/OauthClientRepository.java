package org.opencloudengine.garuda.web.console.oauthclient;

import java.util.List;

public interface OauthClientRepository {

    OauthClient insert(OauthClient OauthClient);

    OauthClient selectById(String id);

    List<OauthClient> selectByManagementId(String managementId);

    OauthClient selectByManagementIdAndName(String managementId, String name);

    OauthClient selectByManagementIdAndId(String managementId, String id);

    OauthClient selectByClientKey(String clientKey);

    OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret);

    OauthClient updateById(OauthClient OauthClient);

    void deleteById(String id);
}
