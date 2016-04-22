package org.opencloudengine.garuda.web.management;

import java.util.List;
import java.util.Map;

public interface ManagementRepository {
    String NAMESPACE = ManagementRepository.class.getName();

    int insert(Management management);

    Management selectById(Long id);

    Management selectByKey(String groupKey);

    Management selectByUserIdAndId(Long userId, Long id);

    List<Management> selectByUserId(Long userId);

    Management selectByCredential(String groupKey, String groupSecret);

    List<Management> selectByCondition(Management management);

    int updateById(Long id, String groupName, String description,Integer sessionTokenLifetime, Integer scopeCheckLifetime);

    int deleteById(Long id);
}
