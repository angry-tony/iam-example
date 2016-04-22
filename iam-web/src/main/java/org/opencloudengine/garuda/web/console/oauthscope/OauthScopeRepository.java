package org.opencloudengine.garuda.web.console.oauthscope;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;

import java.util.List;

public interface OauthScopeRepository {
    String NAMESPACE = OauthScopeRepository.class.getName();

    int insert(OauthScope oauthUser);

    OauthScope selectById(Long id);

    List<OauthScope> selectByGroupId(Long groupId);

    OauthScope selectByGroupIdAndName(Long groupId, String name);

    OauthScope selectByGroupIdAndId(Long groupId, Long id);

    List<OauthScope> selectByCondition(OauthScope oauthScope);

    int updateById(OauthScope oauthScope);

    int deleteById(Long id);

    int insertClientScopes(OauthClientScopes oauthClientScopes);

    List<OauthScope> selectClientScopes(Long clientId);

    OauthScope selectClientScopesByScopeId(Long clientId, Long scopeId);

    int deleteClientScopes(Long clientId);

    int deleteClientScopesByScopeId(Long clientId, Long scopeId);
}
