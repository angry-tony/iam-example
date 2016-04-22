package org.opencloudengine.garuda.web.console.oauthuser;

import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OauthUserRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements OauthUserRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public OauthUserRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(OauthUser oauthUser) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insert", oauthUser);
    }

    @Override
    public OauthUser selectById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectById", id);
    }

    @Override
    public List<OauthUser> selectByGroupId(Long groupId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByGroupId", groupId);
    }

    @Override
    public OauthUser selectByGroupIdAndUserName(Long groupId, String userName) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("userName", userName);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndUserName", map);
    }

    @Override
    public OauthUser selectByGroupIdAndCredential(Long groupId, String userName, String userPassword) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("userName", userName);
        map.put("userPassword", userPassword);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndCredential", map);
    }

    @Override
    public OauthUser selectByGroupIdAndId(Long groupId, Long id) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("id", id);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndId", map);
    }

    @Override
    public int updateById(OauthUser oauthUser) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateById", oauthUser);
    }

    @Override
    public int deleteById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteById", id);
    }

    @Override
    public List<OauthUser> selectByCondition(OauthUser oauthUser) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByCondition", oauthUser);
    }
}
