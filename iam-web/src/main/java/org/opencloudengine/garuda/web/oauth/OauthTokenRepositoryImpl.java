package org.opencloudengine.garuda.web.oauth;

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
public class OauthTokenRepositoryImpl extends PersistentRepositoryImpl<String, Object> implements OauthTokenRepository {

    @Override
    public String getNamespace() {
        return this.NAMESPACE;
    }

    @Autowired
    public OauthTokenRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insertCode(OauthCode oauthCode) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insertCode", oauthCode);
    }

    @Override
    public OauthCode selectCodeById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCodeById", id);
    }

    @Override
    public OauthCode selectCodeByCode(String code) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCodeByCode", code);
    }

    @Override
    public OauthCode selectCodeByCodeAndClientId(String code, Long clientId) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("clientId", clientId);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectCodeByCodeAndClientId", map);
    }

    @Override
    public int updateCodeById(Long id) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateCodeById", id);
    }

    @Override
    public int deleteCodeById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteCodeById", id);
    }

    @Override
    public int insertToken(OauthAccessToken oauthAccessToken) {
        return this.getSqlSessionTemplate().insert(this.getNamespace() + ".insertToken", oauthAccessToken);
    }

    @Override
    public OauthCode selectTokenById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenById", id);
    }

    @Override
    public OauthCode selectTokenByToken(String token) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenByToken", token);
    }

    @Override
    public int updateTokenById(Long id) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateTokenById", id);
    }

    @Override
    public int deleteTokenById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteTokenById", id);
    }
}
