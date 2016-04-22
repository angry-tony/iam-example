package org.opencloudengine.garuda.web.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OauthTokenService {

    int insertCode(OauthCode oauthCode);

    OauthCode selectCodeById(Long id);

    OauthCode selectCodeByCode(String code);

    OauthCode selectCodeByCodeAndClientId(String code, Long clientId);

    List<OauthCode> selectCodeByCondition(OauthCode oauthCode);

    int updateCodeById(Long id);

    int deleteCodeById(Long id);

    OauthAccessToken insertToken(OauthAccessToken oauthAccessToken);

    OauthAccessToken selectTokenById(Long id);

    OauthAccessToken selectTokenByToken(String token);

    OauthAccessToken selectTokenByRefreshToken(String refreshToken);

    OauthAccessToken selectTokenByGroupIdAndId(Long groupId, Long id);

    List<OauthAccessToken> selectTokenByCondition(OauthAccessToken oauthAccessToken);

    int updateTokenById(OauthAccessToken oauthAccessToken);

    int deleteTokenById(Long id);
}
