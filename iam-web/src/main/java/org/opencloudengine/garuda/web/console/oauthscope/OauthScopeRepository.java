package org.opencloudengine.garuda.web.console.oauthscope;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;

import java.io.IOException;
import java.util.List;

public interface OauthScopeRepository {
    String NAMESPACE = OauthScopeRepository.class.getName();

    OauthScope insert(OauthScope oauthUser);

    OauthScope selectById(String id);

    List<OauthScope> selectByManagementId(String managementId);

    OauthScope selectByManagementIdAndName(String managementId, String name);

    OauthScope selectByManagementIdAndId(String managementId, String id);

    OauthScope updateById(OauthScope oauthScope);

    void deleteById(String id);

    OauthClientScopes insertClientScopes(OauthClientScopes oauthClientScopes);

    List<OauthScope> selectClientScopes(String clientId);

    OauthScope selectClientScopesByScopeId(String clientId, String scopeId);

    void deleteClientScopes(String clientId);

    void deleteClientScopesByScopeId(String clientId, String scopeId);
}
