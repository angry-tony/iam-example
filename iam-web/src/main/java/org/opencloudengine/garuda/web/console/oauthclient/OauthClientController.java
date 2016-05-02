package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.common.exception.ServiceException;
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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/console/client")
public class OauthClientController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private UserService userService;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private OauthScopeService oauthScopeService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list(HttpSession session) {
        Management management = (Management) session.getAttribute("management");

        List<OauthClient> oauthClients = oauthClientService.selectByManagementId(management.get_id());
        ModelAndView mav = new ModelAndView("/console/client/list");

        mav.addObject("management", management);
        mav.addObject("oauthClients", oauthClients);
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView newManagement(HttpSession session) {
        Management management = (Management) session.getAttribute("management");
        List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());

        ModelAndView mav = new ModelAndView("/console/client/new");

        mav.addObject("management", management);
        mav.addObject("oauthScopes", oauthScopes);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView create(HttpSession session,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String description,
                               @RequestParam(defaultValue = "") String clientTrust,
                               @RequestParam(defaultValue = "") String clientType,
                               @RequestParam(defaultValue = "N") String activeClient,
                               @RequestParam(defaultValue = "") String authorizedGrantTypes,
                               @RequestParam(defaultValue = "") String webServerRedirectUri,
                               @RequestParam(defaultValue = "") String refreshTokenValidity,
                               @RequestParam(defaultValue = "") String additionalInformation,
                               @RequestParam(defaultValue = "") Integer codeLifetime,
                               @RequestParam(defaultValue = "") Integer refreshTokenLifetime,
                               @RequestParam(defaultValue = "") Integer accessTokenLifetime,
                               @RequestParam(defaultValue = "") Integer jwtTokenLifetime,
                               @RequestParam(defaultValue = "") String scopes
    ) throws IOException {


        Management management = (Management) session.getAttribute("management");
        List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());

        try {

            //같은 이름 검색
            OauthClient existClient = oauthClientService.selectByManagementIdAndName(management.get_id(), name);
            if (existClient != null) {
                ModelAndView mav = new ModelAndView("/console/client/new");
                mav.addObject("management", management);
                mav.addObject("oauthScopes", oauthScopes);
                mav.addObject("duplicate", true);
                return mav;
            }

            //클라이언트 생성
            oauthClientService.createClient(management.get_id(), name, description, clientTrust, clientType, activeClient, authorizedGrantTypes,
                    webServerRedirectUri, refreshTokenValidity, codeLifetime,
                    refreshTokenLifetime, accessTokenLifetime, jwtTokenLifetime, scopes);

            //리스트 페이지 반환
            List<OauthClient> oauthClients = oauthClientService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/client/list");
            mav.addObject("management", management);
            mav.addObject("oauthClients", oauthClients);
            return mav;

        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/client/new");
            mav.addObject("management", management);
            mav.addObject("oauthScopes", oauthScopes);
            mav.addObject("failed", true);
            return mav;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView edit(HttpSession session,
                             @RequestParam(defaultValue = "") String _id) throws IOException {

        Management management = (Management) session.getAttribute("management");
        List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());

        //클라이언트 검색
        OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
        if (oauthClient == null) {
            throw new ServiceException("Invalid oauth client id");
        }

        //스코프 검색
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(_id);
        try {

            ModelAndView mav = new ModelAndView("/console/client/edit");
            mav.addObject("management", management);
            mav.addObject("oauthClient", oauthClient);
            mav.addObject("oauthScopes", oauthScopes);
            mav.addObject("clientScopes", clientScopes);
            return mav;
        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth client id");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView delete(HttpSession session,
                               @RequestParam(defaultValue = "") String _id) throws IOException {

        Management management = (Management) session.getAttribute("management");

        try {
            //클라이언트 검색
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthClient == null) {
                throw new ServiceException("Invalid oauth client id");
            }

            oauthClientService.deleteById(_id);

            List<OauthClient> oauthClients = oauthClientService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/client/list");
            mav.addObject("management", management);
            mav.addObject("oauthClients", oauthClients);
            return mav;

        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth client id");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView update(HttpSession session,
                               @RequestParam(defaultValue = "") String _id,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String description,
                               @RequestParam(defaultValue = "") String clientTrust,
                               @RequestParam(defaultValue = "") String clientType,
                               @RequestParam(defaultValue = "N") String activeClient,
                               @RequestParam(defaultValue = "") String authorizedGrantTypes,
                               @RequestParam(defaultValue = "") String webServerRedirectUri,
                               @RequestParam(defaultValue = "") String refreshTokenValidity,
                               @RequestParam(defaultValue = "") String additionalInformation,
                               @RequestParam(defaultValue = "") Integer codeLifetime,
                               @RequestParam(defaultValue = "") Integer refreshTokenLifetime,
                               @RequestParam(defaultValue = "") Integer accessTokenLifetime,
                               @RequestParam(defaultValue = "") Integer jwtTokenLifetime,
                               @RequestParam(defaultValue = "") String scopes
    ) throws IOException {

        Management management = (Management) session.getAttribute("management");
        List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());

        //클라이언트 검색
        OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
        if (oauthClient == null) {
            throw new ServiceException("Invalid oauth client id");
        }

        //스코프 검색
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(_id);
        try {

            //같은 이름 검색
            OauthClient existClient = oauthClientService.selectByManagementIdAndName(management.get_id(), name);
            if (existClient != null) {
                if (!existClient.get_id().equals(_id)) {
                    ModelAndView mav = new ModelAndView("/console/client/edit");
                    mav.addObject("management", management);
                    mav.addObject("oauthClient", oauthClient);
                    mav.addObject("clientScopes", clientScopes);
                    mav.addObject("duplicate", true);
                    return mav;
                }
            }

            oauthClientService.updateById(_id, name, description, clientTrust, clientType, activeClient, authorizedGrantTypes,
                    webServerRedirectUri, refreshTokenValidity, codeLifetime,
                    refreshTokenLifetime, accessTokenLifetime, jwtTokenLifetime, scopes);

            List<OauthClient> oauthClients = oauthClientService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/client/list");
            mav.addObject("management", management);
            mav.addObject("oauthClients", oauthClients);
            return mav;
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/client/edit");
            mav.addObject("management", management);
            mav.addObject("oauthClient", oauthClient);
            mav.addObject("oauthScopes", oauthScopes);
            mav.addObject("clientScopes", clientScopes);
            mav.addObject("failed", true);
            return mav;
        }
    }
}
