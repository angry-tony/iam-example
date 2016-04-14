package org.opencloudengine.garuda.web.download;

import org.opencloudengine.garuda.common.repository.PersistentRepository;

public interface DownloadRepository extends PersistentRepository<Download, Long> {

    public static final String NAMESPACE = DownloadRepository.class.getName();

}
