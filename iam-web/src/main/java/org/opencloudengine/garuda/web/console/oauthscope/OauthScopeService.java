package org.opencloudengine.garuda.web.console.oauthscope;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;

import java.util.List;

public interface OauthScopeService {

    OauthScope selectById(Long id);

    List<OauthScope> selectByGroupId(Long groupId);

    OauthScope selectByGroupIdAndId(Long groupId, Long id);

    OauthScope selectByGroupIdAndName(Long groupId, String name);

    int updateById(Long id, String name, String description, String additionalInformation);

    int deleteById(Long id);

    int createScope(Long groupId, String name, String description, String additionalInformation);

    int insertClientScopes(OauthClientScopes oauthClientScopes);

    List<OauthScope> selectClientScopes(Long clientId);

    int deleteClientScopes(Long clientId);
}
