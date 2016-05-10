package org.opencloudengine.garuda.web.management;

import java.util.List;
import java.util.Map;

public interface ManagementRepository {

    Management insert(Management management);

    Management selectById(String id);

    Management selectByKey(String managementKey);

    Management selectByUserIdAndId(String userId, String id);

    List<Management> selectByUserId(String userId);

    Management selectByCredential(String managementKey, String managementSecret);

    Management updateById(String id, String managementName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime);

    Management updateById(Management management);

    void deleteById(String id);
}
