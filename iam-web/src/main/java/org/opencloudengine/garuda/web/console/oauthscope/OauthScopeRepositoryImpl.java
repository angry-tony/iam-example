package org.opencloudengine.garuda.web.console.oauthscope;

import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OauthScopeRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements OauthScopeRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public OauthScopeRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(OauthScope OauthScope) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insert", OauthScope);
    }

    @Override
    public OauthScope selectById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectById", id);
    }

    @Override
    public List<OauthScope> selectByGroupId(Long groupId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByGroupId", groupId);
    }

    @Override
    public OauthScope selectByGroupIdAndName(Long groupId, String name) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("name", name);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndName", map);
    }

    @Override
    public OauthScope selectByGroupIdAndId(Long groupId, Long id) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("id", id);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndId", map);
    }

    @Override
    public int updateById(OauthScope OauthScope) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateById", OauthScope);
    }

    @Override
    public int deleteById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteById", id);
    }

    @Override
    public int insertClientScopes(OauthClientScopes oauthClientScopes) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insertClientScopes", oauthClientScopes);
    }

    @Override
    public List<OauthScope> selectClientScopes(Long clientId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectClientScopes", clientId);
    }

    @Override
    public int deleteClientScopes(Long clientId) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteClientScopes", clientId);
    }
}
