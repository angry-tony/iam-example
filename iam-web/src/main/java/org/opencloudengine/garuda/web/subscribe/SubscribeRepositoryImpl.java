package org.opencloudengine.garuda.web.subscribe;

import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SubscribeRepositoryImpl extends PersistentRepositoryImpl<Map, Long> implements SubscribeRepository {

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Autowired
    public SubscribeRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

}
