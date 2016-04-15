package org.opencloudengine.garuda.web.oauth;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private UserService userService;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private OauthScopeService oauthScopeService;


    @RequestMapping(value = "/authorize", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void list(HttpServletRequest req, HttpServletResponse res) throws IOException {


        res.sendRedirect("/");
    }
}
