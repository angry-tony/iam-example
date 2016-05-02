package org.opencloudengine.garuda.web.console.oauthscope;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;

import java.io.IOException;
import java.util.List;

public interface OauthScopeService {

    OauthScope selectById(String id);

    List<OauthScope> selectByManagementId(String managementId);

    OauthScope selectByManagementIdAndId(String managementId, String id);

    OauthScope selectByManagementIdAndName(String managementId, String name);

    OauthScope updateById(String id, String name, String description);

    OauthScope updateById(OauthScope oauthScope);

    void deleteById(String id);

    OauthScope createScope(String managementId, String name, String description);

    OauthClientScopes insertClientScopes(OauthClientScopes oauthClientScopes);

    List<OauthScope> selectClientScopes(String clientId);

    OauthScope selectClientScopesByScopeId(String clientId, String scopeId);

    void deleteClientScopes(String clientId);

    void deleteClientScopesByScopeId(String clientId, String scopeId);
}
