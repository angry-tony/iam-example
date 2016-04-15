package org.opencloudengine.garuda.web.oauth;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OauthService {

    AuthorizeResponse processAuthorize(HttpServletRequest request);
}
