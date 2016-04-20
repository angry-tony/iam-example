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
    public int updateCodeById(Long id) {
        return oauthTokenRepository.updateCodeById(id);
    }

    @Override
    public int deleteCodeById(Long id) {
        return oauthTokenRepository.deleteCodeById(id);
    }

    @Override
    public int insertToken(OauthAccessToken oauthAccessToken) {
        return oauthTokenRepository.insertToken(oauthAccessToken);
    }

    @Override
    public OauthCode selectTokenById(Long id) {
        return oauthTokenRepository.selectTokenById(id);
    }

    @Override
    public OauthCode selectTokenByToken(String token) {
        return oauthTokenRepository.selectTokenByToken(token);
    }

    @Override
    public int updateTokenById(Long id) {
        return oauthTokenRepository.updateTokenById(id);
    }

    @Override
    public int deleteTokenById(Long id) {
        return oauthTokenRepository.deleteTokenById(id);
    }
}
