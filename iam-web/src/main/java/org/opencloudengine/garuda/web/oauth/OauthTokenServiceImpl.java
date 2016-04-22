package org.opencloudengine.garuda.web.oauth;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.HttpUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthTokenRepository oauthTokenRepository;

    @Override
    public int insertCode(OauthCode oauthCode) {
        return oauthTokenRepository.insertCode(oauthCode);
    }

    @Override
    public OauthCode selectCodeById(Long id) {
        return oauthTokenRepository.selectCodeById(id);
    }

    @Override
    public OauthCode selectCodeByCode(String code) {
        return oauthTokenRepository.selectCodeByCode(code);
    }

    @Override
    public OauthCode selectCodeByCodeAndClientId(String code, Long clientId) {
        return oauthTokenRepository.selectCodeByCodeAndClientId(code, clientId);
    }

    @Override
    public List<OauthCode> selectCodeByCondition(OauthCode oauthCode) {
        return oauthTokenRepository.selectCodeByCondition(oauthCode);
    }

    @Override
    public int updateCodeById(Long id) {
        return oauthTokenRepository.updateCodeById(id);
    }

    @Override
    public int deleteCodeById(Long id) {
        return oauthTokenRepository.deleteCodeById(id);
    }

    @Override
    public OauthAccessToken insertToken(OauthAccessToken oauthAccessToken) {
        oauthTokenRepository.insertToken(oauthAccessToken);
        return oauthAccessToken;
    }

    @Override
    public OauthAccessToken selectTokenById(Long id) {
        return oauthTokenRepository.selectTokenById(id);
    }

    @Override
    public OauthAccessToken selectTokenByToken(String token) {
        return oauthTokenRepository.selectTokenByToken(token);
    }

    @Override
    public OauthAccessToken selectTokenByRefreshToken(String refreshToken) {
        return oauthTokenRepository.selectTokenByRefreshToken(refreshToken);
    }

    @Override
    public OauthAccessToken selectTokenByGroupIdAndId(Long groupId, Long id) {
        return oauthTokenRepository.selectTokenByGroupIdAndId(groupId, id);
    }

    @Override
    public List<OauthAccessToken> selectTokenByCondition(OauthAccessToken oauthAccessToken) {
        return oauthTokenRepository.selectTokenByCondition(oauthAccessToken);
    }

    @Override
    public int updateTokenById(OauthAccessToken oauthAccessToken) {
        return oauthTokenRepository.updateTokenById(oauthAccessToken);
    }

    @Override
    public int deleteTokenById(Long id) {
        return oauthTokenRepository.deleteTokenById(id);
    }
}
