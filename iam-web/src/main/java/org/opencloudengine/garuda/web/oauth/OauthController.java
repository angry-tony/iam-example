package org.opencloudengine.garuda.web.oauth;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.JsonUtils;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private OauthClientService oauthClientService;


    @RequestMapping(value = "/authorize", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        AuthorizeResponse authorizeResponse = oauthService.validateAuthorize(request);

        if(authorizeResponse.getError() != null){
            Map map = new HashMap();
            map.put("error" , authorizeResponse.getError());
            map.put("error_description" , authorizeResponse.getError_description());
            map.put("state", authorizeResponse.getState());
            String marshal = JsonUtils.marshal(map);
            response.getWriter().write(marshal);

            oauthService.redirectAuthorize(authorizeResponse);

            return null;
        }else{
            //인증 화면으로 넘어갈 것.
            ModelAndView mav = new ModelAndView("/auth/oauth-login");
            mav.addObject("authorizeResponse", authorizeResponse);
            return mav;
        }
    }

    @RequestMapping(value = "/authorize_redirect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void authorize_redirect(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        String error_description = request.getParameter("error_description");
        System.out.println(error_description);
    }
}
