package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.model.User;

import java.util.List;
import java.util.Map;

public interface ManagementService {

    List<Management> selectByUserId(Long userId);

    Management selectById(Long id);

    Management selectByKey(String groupKey);

    Management selectByUserIdAndId(Long userId, Long id);

    Management selectByCredential(String groupKey, String groupSecret);

    List<Management> selectByCondition(Management management);

    int createManagement(Long userId, String groupName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime);

    int deleteById(Long id);

    int updateById(Long userId, Long id, String groupName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime);
}
