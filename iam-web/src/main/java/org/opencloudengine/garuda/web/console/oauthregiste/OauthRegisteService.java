package org.opencloudengine.garuda.web.console.oauthregiste;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthuser.OauthScopeToken;
import org.opencloudengine.garuda.web.console.oauthuser.OauthSessionToken;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;

import java.io.InputStream;
import java.util.List;

public interface OauthRegisteService {

    OauthRegiste singUp(OauthClient oauthClient, OauthUser oauthUser, String redirect_url) throws Exception;

    OauthRegiste forgotPassword(OauthClient oauthClient, OauthUser oauthUser, String redirect_url) throws Exception;

    OauthUser verification(OauthClient oauthClient, String token, String notification_type) throws Exception;

    OauthUser acceptSingUp(OauthClient oauthClient, String token) throws Exception;

    OauthUser acceptPassword(OauthClient oauthClient, String token, String password) throws Exception;

    OauthUser rePassword(OauthClient oauthClient, OauthUser oauthUser, String beforePassword, String afterPassword) throws Exception;

    OauthRegiste selectById(String id);

    OauthRegiste selectByClientIdAndTokenAndType(String clientId, String token, String notification_type);
}
