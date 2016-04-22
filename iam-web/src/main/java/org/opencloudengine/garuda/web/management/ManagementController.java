package org.opencloudengine.garuda.web.management;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.common.rest.Response;
import org.opencloudengine.garuda.common.security.SessionUtils;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/management")
public class ManagementController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private UserService userService;

    @Autowired
    private ManagementService managementService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list(HttpSession session) {
        ModelAndView mav = new ModelAndView("/management/list");

        List<Management> managements = managementService.selectByUserId(SessionUtils.getId());
        mav.addObject("managements", managements);
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView newManagement(HttpSession session) {
        ModelAndView mav = new ModelAndView("/management/new");
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView create(HttpSession session,
                               @RequestParam(defaultValue = "") String groupName,
                               @RequestParam(defaultValue = "") String description,
                               @RequestParam(defaultValue = "") Integer sessionTokenLifetime,
                               @RequestParam(defaultValue = "") Integer scopeCheckLifetime) throws IOException {

        try {
            managementService.createManagement(SessionUtils.getId(), groupName, description, sessionTokenLifetime, scopeCheckLifetime);
            ModelAndView mav = new ModelAndView("/management/list");

            List<Management> managements = managementService.selectByUserId(SessionUtils.getId());
            mav.addObject("managements", managements);
            return mav;
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/management/new");
            mav.addObject("failed", true);
            return mav;
        }
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView session(HttpSession session,
                                @RequestParam(defaultValue = "") Long groupId) throws IOException {

        try {
            Management management = managementService.selectByUserIdAndId(SessionUtils.getId(), groupId);
            if (management != null) {
                session.setAttribute("management", management);
                ModelAndView mav = new ModelAndView("/console/overview");
                mav.addObject("management", management);
                return mav;
            } else {
                session.removeAttribute("management");
                throw new ServiceException("Invalid management groupId");
            }
        } catch (Exception ex) {
            session.removeAttribute("management");
            throw new ServiceException("Invalid management groupId");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView edit(HttpSession session,
                             @RequestParam(defaultValue = "") Long groupId) throws IOException {

        try {
            Management management = managementService.selectByUserIdAndId(SessionUtils.getId(), groupId);
            if (management != null) {
                ModelAndView mav = new ModelAndView("/management/edit");
                mav.addObject("management", management);
                return mav;
            } else {
                throw new ServiceException("Invalid management groupId");
            }
        } catch (Exception ex) {
            throw new ServiceException("Invalid management groupId");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView delete(HttpSession session,
                               @RequestParam(defaultValue = "") Long groupId) throws IOException {

        try {
            Management management = managementService.selectByUserIdAndId(SessionUtils.getId(), groupId);
            if (management != null) {
                managementService.deleteById(groupId);
                ModelAndView mav = new ModelAndView("/management/list");
                List<Management> managements = managementService.selectByUserId(SessionUtils.getId());
                mav.addObject("managements", managements);
                return mav;
            } else {
                throw new ServiceException("Invalid groupId");
            }
        } catch (Exception ex) {
            throw new ServiceException("Invalid groupId");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView update(HttpSession session,
                               @RequestParam(defaultValue = "") Long groupId,
                               @RequestParam(defaultValue = "") String groupName,
                               @RequestParam(defaultValue = "") String description,
                               @RequestParam(defaultValue = "") Integer sessionTokenLifetime,
                               @RequestParam(defaultValue = "") Integer scopeCheckLifetime) throws IOException {

        try {
            managementService.updateById(SessionUtils.getId(), groupId, groupName, description, sessionTokenLifetime, scopeCheckLifetime);

            ModelAndView mav = new ModelAndView("/management/list");
            List<Management> managements = managementService.selectByUserId(SessionUtils.getId());
            mav.addObject("managements", managements);
            return mav;
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/management/edit");
            mav.addObject("failed", true);
            return mav;
        }
    }
}
