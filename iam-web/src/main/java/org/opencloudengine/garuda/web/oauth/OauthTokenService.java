package org.opencloudengine.garuda.web.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OauthTokenService {

    int insertCode(OauthCode oauthCode);

    OauthCode selectCodeById(Long id);

    OauthCode selectCodeByCode(String code);

    OauthCode selectCodeByCodeAndClientId(String code, Long clientId);

    int updateCodeById(Long id);

    int deleteCodeById(Long id);

    int insertToken(OauthAccessToken oauthAccessToken);

    OauthCode selectTokenById(Long id);

    OauthCode selectTokenByToken(String token);

    int updateTokenById(Long id);

    int deleteTokenById(Long id);
}
