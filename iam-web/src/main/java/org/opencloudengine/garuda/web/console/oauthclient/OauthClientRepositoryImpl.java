package org.opencloudengine.garuda.web.console.oauthclient;

import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OauthClientRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements OauthClientRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public OauthClientRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(OauthClient OauthClient) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insert", OauthClient);
    }

    @Override
    public OauthClient selectById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectById", id);
    }

    @Override
    public List<OauthClient> selectByGroupId(Long groupId) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByGroupId", groupId);
    }

    @Override
    public OauthClient selectByGroupIdAndName(Long groupId, String name) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("name", name);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndName", map);
    }

    @Override
    public OauthClient selectByGroupIdAndId(Long groupId, Long id) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("id", id);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByGroupIdAndId", map);
    }

    @Override
    public OauthClient selectByClientKey(String clientKey) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByClientKey", clientKey);
    }

    @Override
    public OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret) {
        Map map = new HashMap();
        map.put("clientKey", clientKey);
        map.put("clientSecret", clientSecret);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByClientKeyAndSecret", map);
    }

    @Override
    public List<OauthClient> selectByCondition(OauthClient oauthClient) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectByCondition", oauthClient);
    }

    @Override
    public int updateById(OauthClient OauthClient) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateById", OauthClient);
    }

    @Override
    public int deleteById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteById", id);
    }

}
