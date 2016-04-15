package org.opencloudengine.garuda.web.oauth;

import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientRepository;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class OauthServiceImpl implements OauthService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Autowired
    private ManagementService managementService;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Override
    public AuthorizeResponse processAuthorize(HttpServletRequest request) {


        return null;
    }
}
