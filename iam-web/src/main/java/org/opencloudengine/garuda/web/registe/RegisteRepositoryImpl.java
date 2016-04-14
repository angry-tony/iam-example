package org.opencloudengine.garuda.web.registe;

import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisteRepositoryImpl extends PersistentRepositoryImpl<Registe, Long> implements RegisteRepository {

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Autowired
    public RegisteRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public Registe selectByUserEmail(Registe registe) {
        return this.getSqlSessionTemplate().selectOne(this.getNamespace() + ".selectByUseridAndToken", registe);
    }
}
