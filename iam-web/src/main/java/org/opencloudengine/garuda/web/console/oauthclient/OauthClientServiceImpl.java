package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserRepository;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class OauthClientServiceImpl implements OauthClientService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Override
    public OauthUser selectById(Long id) {
        return oauthClientRepository.selectById(id);
    }

    @Override
    public List<OauthUser> selectByGroupId(Long groupId) {
        return oauthClientRepository.selectByGroupId(groupId);
    }

    @Override
    public OauthUser selectByGroupIdAndUserName(Long groupId, String userName) {
        return oauthClientRepository.selectByGroupIdAndUserName(groupId, userName);
    }

    @Override
    public OauthUser selectByGroupIdAndId(Long groupId, Long id) {
        return oauthClientRepository.selectByGroupIdAndId(groupId, id);
    }

    @Override
    public int updateById(Long id, String userName, String userPassword, int level, String additionalInformation) {
        OauthUser oauthUser = new OauthUser();
        oauthUser.setId(id);
        oauthUser.setUserName(userName);
        oauthUser.setUserPassword(userPassword);
        oauthUser.setLevel(level);
        oauthUser.setAdditionalInformation(additionalInformation);
        return oauthClientRepository.updateById(oauthUser);
    }

    @Override
    public int deleteById(Long id) {
        return oauthClientRepository.deleteById(id);
    }

    @Override
    public int createUser(Long groupId, String userName, String userPassword, int level, String additionalInformation) {
        OauthUser oauthUser = new OauthUser();
        oauthUser.setGroupId(groupId);
        oauthUser.setUserName(userName);
        oauthUser.setUserPassword(userPassword);
        oauthUser.setLevel(level);
        oauthUser.setAdditionalInformation(additionalInformation);
        return oauthClientRepository.insert(oauthUser);
    }
}
