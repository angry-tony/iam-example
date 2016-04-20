package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Service
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private ManagementRepository managementRepository;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Override
    public List<Management> selectByUserId(Long userId) {
        return managementRepository.selectByUserId(userId);
    }

    @Override
    public Management selectById(Long id) {
        return managementRepository.selectById(id);
    }

    @Override
    public Management selectByKey(String groupKey) {
        return managementRepository.selectByKey(groupKey);
    }

    @Override
    public Management selectByUserIdAndId(Long userId, Long id) {
        return managementRepository.selectByUserIdAndId(userId, id);
    }

    @Override
    public int createManagement(Long userId, String groupName, String description, int sessionTokenLifetime, int scopeCheckLifetime) {
        Management management = new Management();
        management.setUserId(userId);
        management.setGroupName(groupName);
        management.setDescription(description);
        management.setSessionTokenLifetime(sessionTokenLifetime);
        management.setScopeCheckLifetime(scopeCheckLifetime);

        //클라이언트 키
        String groupKey = UUID.randomUUID().toString();
        String groupSecret = UUID.randomUUID().toString();
        String groupJwtSecret = UUID.randomUUID().toString();

        management.setGroupKey(groupKey);
        management.setGroupSecret(groupSecret);
        management.setGroupJwtSecret(groupJwtSecret);

        return managementRepository.insert(management);
    }

    @Override
    public int deleteById(Long id) {
        return managementRepository.deleteById(id);
    }

    @Override
    public int updateById(Long userId, Long id, String groupName, String description, int sessionTokenLifetime, int scopeCheckLifetime) {
        Management management = this.selectByUserIdAndId(userId, id);
        if (management == null) {
            throw new ServiceException("Invalid management groupId");
        }

        return managementRepository.updateById(id, groupName, description, sessionTokenLifetime, scopeCheckLifetime);
    }
}
