package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementRepository;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class OauthUserServiceImpl implements OauthUserService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthUserRepository oauthUserRepository;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Override
    public OauthUser selectById(Long id) {
        return oauthUserRepository.selectById(id);
    }

    @Override
    public List<OauthUser> selectByGroupId(Long groupId) {
        return oauthUserRepository.selectByGroupId(groupId);
    }

    @Override
    public OauthUser selectByGroupIdAndUserName(Long groupId, String userName) {
        return oauthUserRepository.selectByGroupIdAndUserName(groupId, userName);
    }

    @Override
    public OauthUser selectByGroupIdAndId(Long groupId, Long id) {
        return oauthUserRepository.selectByGroupIdAndId(groupId, id);
    }

    @Override
    public int updateById(Long id, String userName, String userPassword, int level, String additionalInformation) {
        OauthUser oauthUser = new OauthUser();
        oauthUser.setId(id);
        oauthUser.setUserName(userName);
        oauthUser.setUserPassword(userPassword);
        oauthUser.setLevel(level);
        oauthUser.setAdditionalInformation(additionalInformation);
        return oauthUserRepository.updateById(oauthUser);
    }

    @Override
    public int deleteById(Long id) {
        return oauthUserRepository.deleteById(id);
    }

    @Override
    public int createUser(Long groupId, String userName, String userPassword, int level, String additionalInformation) {
        OauthUser oauthUser = new OauthUser();
        oauthUser.setGroupId(groupId);
        oauthUser.setUserName(userName);
        oauthUser.setUserPassword(userPassword);
        oauthUser.setLevel(level);
        oauthUser.setAdditionalInformation(additionalInformation);
        return oauthUserRepository.insert(oauthUser);
    }
}
