package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;

import java.util.List;

public interface OauthClientService {

    OauthUser selectById(Long id);

    List<OauthUser> selectByGroupId(Long groupId);

    OauthUser selectByGroupIdAndUserName(Long groupId, String userName);

    OauthUser selectByGroupIdAndId(Long groupId, Long id);

    int updateById(Long id, String userName, String userPassword, int level, String additionalInformation);

    int deleteById(Long id);

    int createUser(Long groupId, String userName, String userPassword, int level, String additionalInformation);
}
