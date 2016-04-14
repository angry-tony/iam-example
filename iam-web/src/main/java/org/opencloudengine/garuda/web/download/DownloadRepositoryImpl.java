package org.opencloudengine.garuda.web.download;

import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DownloadRepositoryImpl extends PersistentRepositoryImpl<Download, Long> implements DownloadRepository {

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Autowired
    public DownloadRepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

}
