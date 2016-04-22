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
    public List<OauthCode> selectCodeByCondition(OauthCode oauthCode) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectCodeByCondition", oauthCode);
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
    public OauthAccessToken selectTokenById(Long id) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenById", id);
    }

    @Override
    public OauthAccessToken selectTokenByToken(String token) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenByToken", token);
    }

    @Override
    public OauthAccessToken selectTokenByRefreshToken(String refreshToken) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenByRefreshToken", refreshToken);
    }

    @Override
    public OauthAccessToken selectTokenByGroupIdAndId(Long groupId, Long id) {
        Map map = new HashMap();
        map.put("groupId", groupId);
        map.put("id", id);
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectTokenByGroupIdAndId", map);
    }

    @Override
    public List<OauthAccessToken> selectTokenByCondition(OauthAccessToken oauthAccessToken) {
        return this.getSqlSessionTemplate().selectList(this.getNamespace() + ".selectTokenByCondition", oauthAccessToken);
    }

    @Override
    public int updateTokenById(OauthAccessToken oauthAccessToken) {
        return this.getSqlSessionTemplate().update(this.getNamespace() + ".updateTokenById", oauthAccessToken);
    }

    @Override
    public int deleteTokenById(Long id) {
        return this.getSqlSessionTemplate().delete(this.getNamespace() + ".deleteTokenById", id);
    }
}
