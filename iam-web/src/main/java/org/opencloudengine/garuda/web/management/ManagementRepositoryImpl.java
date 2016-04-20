package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ManagementRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements ManagementRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public ManagementRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(Management management) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insert", management);
    }

    @Override
    public Management selectById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectById", id);
    }

    @Override
    public Management selectByKey(String groupKey) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByKey", groupKey);
    }

    @Override
    public Management selectByUserIdAndId(Long userId, Long id) {
        Map map = new HashMap();
        map.put("userId" , userId);
        map.put("id" , id);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByUserIdAndId", map);
    }

    @Override
    public List<Management> selectByUserId(Long userId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByUserId", userId);
    }

    @Override
    public int updateById(Long id, String groupName, String description, int sessionTokenLifetime, int scopeCheckLifetime) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("groupName", groupName);
        map.put("description", description);
        map.put("sessionTokenLifetime", sessionTokenLifetime);
        map.put("scopeCheckLifetime", scopeCheckLifetime);
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateById", map);
    }

    @Override
    public int deleteById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteById", id);
    }
}
