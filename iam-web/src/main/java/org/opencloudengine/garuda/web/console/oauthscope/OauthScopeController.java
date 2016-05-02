package org.opencloudengine.garuda.web.console.oauthscope;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
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
@RequestMapping("/console/scope")
public class OauthScopeController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private UserService userService;

    @Autowired
    private OauthScopeService oauthScopeService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list(HttpSession session) {
        Management management = (Management) session.getAttribute("management");

        List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());
        ModelAndView mav = new ModelAndView("/console/scope/list");

        mav.addObject("management", management);
        mav.addObject("oauthScopes", oauthScopes);
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView newManagement(HttpSession session) {
        Management management = (Management) session.getAttribute("management");

        ModelAndView mav = new ModelAndView("/console/scope/new");

        mav.addObject("management", management);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView create(HttpSession session,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String description
    ) throws IOException {
        Management management = (Management) session.getAttribute("management");

        try {
            //같은 스코프 검색
            OauthScope existScope = oauthScopeService.selectByManagementIdAndName(management.get_id(), name);
            if (existScope != null) {
                ModelAndView mav = new ModelAndView("/console/scope/new");
                mav.addObject("duplicate", true);
                return mav;
            }

            //스코프 생성
            oauthScopeService.createScope(management.get_id(), name, description);

            //리스트 페이지 반환
            List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/scope/list");
            mav.addObject("management", management);
            mav.addObject("oauthScopes", oauthScopes);
            return mav;

        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/scope/new");
            mav.addObject("management", management);
            mav.addObject("failed", true);
            return mav;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView edit(HttpSession session,
                             @RequestParam(defaultValue = "") String _id) throws IOException {

        Management management = (Management) session.getAttribute("management");
        try {
            //스코프 검색
            OauthScope oauthScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthScope == null) {
                throw new ServiceException("Invalid oauth scope id");
            }

            ModelAndView mav = new ModelAndView("/console/scope/edit");
            mav.addObject("management", management);
            mav.addObject("oauthScope", oauthScope);
            return mav;
        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth scope id");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView delete(HttpSession session,
                               @RequestParam(defaultValue = "") String _id) throws IOException {

        Management management = (Management) session.getAttribute("management");

        try {
            //스코프 검색
            OauthScope oauthScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthScope == null) {
                throw new ServiceException("Invalid oauth scope id");
            }

            oauthScopeService.deleteById(_id);

            List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/scope/list");
            mav.addObject("management", management);
            mav.addObject("oauthScopes", oauthScopes);
            return mav;

        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth scope id");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView update(HttpSession session,
                               @RequestParam(defaultValue = "") String _id,
                               @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "") String description,
                               @RequestParam(defaultValue = "") String additionalInformation) throws IOException {

        Management management = (Management) session.getAttribute("management");

        try {
            //스코프 검색
            OauthScope oauthScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthScope == null) {
                throw new ServiceException("Invalid oauth scope id");
            }

            //같은 이름의 스코프 검색
            OauthScope existScope = oauthScopeService.selectByManagementIdAndName(management.get_id(), name);
            if (existScope != null) {
                if (!existScope.get_id().equals(_id)) {
                    ModelAndView mav = new ModelAndView("/console/scope/edit");
                    mav.addObject("management", management);
                    mav.addObject("oauthScope", oauthScope);
                    mav.addObject("duplicate", true);
                    return mav;
                }
            }

            oauthScopeService.updateById(_id, name, description);

            List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/scope/list");
            mav.addObject("management", management);
            mav.addObject("oauthScopes", oauthScopes);
            return mav;
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/scope/edit");
            mav.addObject("management", management);
            mav.addObject("failed", true);
            return mav;
        }
    }
}
