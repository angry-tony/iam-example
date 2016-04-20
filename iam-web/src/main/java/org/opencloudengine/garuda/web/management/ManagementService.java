package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.model.User;

import java.util.List;
import java.util.Map;

public interface ManagementService {

    List<Management> selectByUserId(Long userId);

    Management selectById(Long id);

    Management selectByKey(String groupKey);

    Management selectByUserIdAndId(Long userId, Long id);

    int createManagement(Long userId, String groupName, String description, int sessionTokenLifetime, int scopeCheckLifetime);

    int deleteById(Long id);

    int updateById(Long userId, Long id, String groupName, String description, int sessionTokenLifetime, int scopeCheckLifetime);
}
