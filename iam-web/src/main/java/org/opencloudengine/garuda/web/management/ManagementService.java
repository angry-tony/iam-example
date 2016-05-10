package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.model.User;

import java.util.List;
import java.util.Map;

public interface ManagementService {

    List<Management> selectByUserId(String userId);

    Management selectById(String id);

    Management selectByKey(String managementKey);

    Management selectByUserIdAndId(String userId, String id);

    Management selectByCredential(String managementKey, String managementSecret);

    Management createManagement(String userId, String managementName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime);

    void deleteById(String id);

    Management updateById(Management management);

    Management updateById(String userId, String id, String managementName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime);
}
