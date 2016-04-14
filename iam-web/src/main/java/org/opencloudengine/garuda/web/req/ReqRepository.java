package org.opencloudengine.garuda.web.req;

import org.opencloudengine.garuda.common.repository.PersistentRepository;

public interface ReqRepository extends PersistentRepository<Req, Long> {

    public static final String NAMESPACE = ReqRepository.class.getName();

}
