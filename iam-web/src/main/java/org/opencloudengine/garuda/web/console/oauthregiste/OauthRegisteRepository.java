package org.opencloudengine.garuda.web.console.oauthregiste;

import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;

import java.io.InputStream;
import java.util.List;

public interface OauthRegisteRepository {
    String NAMESPACE = OauthRegisteRepository.class.getName();

    OauthRegiste insert(OauthRegiste oauthRegiste);

    OauthRegiste selectById(String id);

    OauthRegiste selectByClientIdAndTokenAndType(String clientId, String token, String notification_type);

    void deleteById(String id);
}
