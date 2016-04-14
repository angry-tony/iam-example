package org.opencloudengine.garuda.web.subscribe;

import org.opencloudengine.garuda.common.repository.PersistentRepository;

import java.util.Map;

public interface SubscribeRepository extends PersistentRepository<Map, Long> {

    public static final String NAMESPACE = SubscribeRepository.class.getName();

}
