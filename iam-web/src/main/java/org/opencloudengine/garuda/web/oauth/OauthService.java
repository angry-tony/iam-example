package org.opencloudengine.garuda.web.oauth;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface OauthService {

    AuthorizeResponse validateAuthorize(HttpServletRequest request);

    void redirectAuthorize(AuthorizeResponse authorizeResponse);
}
