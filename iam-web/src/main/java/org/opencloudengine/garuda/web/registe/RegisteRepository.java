package org.opencloudengine.garuda.web.registe;

import org.opencloudengine.garuda.common.repository.PersistentRepository;

public interface RegisteRepository extends PersistentRepository<Registe, Long> {

    public static final String NAMESPACE = RegisteRepository.class.getName();

    Registe selectByUserEmail(Registe registe);
}
