package org.opencloudengine.garuda.web.contactus;

import org.opencloudengine.garuda.common.repository.PersistentRepository;

public interface ContactUsRepository extends PersistentRepository<ContactUs, Long> {

    public static final String NAMESPACE = ContactUsRepository.class.getName();

}
