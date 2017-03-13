package org.opencloudengine.garuda.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * SLF4J Logging
     */
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    @Qualifier("config")
    private Properties config;

    /**
     * API 페이지로 이동한다.
     *
     * @return Model And View
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView api(HttpSession session, final Locale locale) {
        session.setAttribute("lang", locale.toString());

        return new ModelAndView("api");
    }


    /**
     * 인덱스 페이지로 이동한다.
     *
     * @return Model And View
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session, final Locale locale) {
        session.setAttribute("lang", locale.toString());

        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(HttpSession session, final Locale locale) {
        session.setAttribute("lang", locale.toString());
        return new ModelAndView("index");
    }

    @RequestMapping(value = "redirect", method = RequestMethod.GET)
    public ModelAndView redirect(@RequestParam String url) {
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "go", method = RequestMethod.GET)
    public ModelAndView go(@RequestParam String url) {
        return new ModelAndView(url);
    }


    @RequestMapping(value = "management/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView managementList() {
        ModelAndView mav = new ModelAndView("/management/list");
        return mav;
    }

    @RequestMapping(value = "management/profile", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView managementProfile() {
        ModelAndView view = new ModelAndView("/management/detail");
        view.addObject("profile", true);
        return view;
    }

    @RequestMapping(value = "management/new", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView newManagement() {
        ModelAndView mav = new ModelAndView("/management/detail");
        return mav;
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView userList() {
        ModelAndView mav = new ModelAndView("/user/list");
        return mav;
    }

    @RequestMapping(value = "user/new", method = RequestMethod.GET)
    public ModelAndView userNew(HttpSession session) {
        ModelAndView view = new ModelAndView("/user/detail");
        return view;
    }

    @RequestMapping(value = "user/{_id}/edit", method = RequestMethod.GET)
    public ModelAndView userEdit(HttpSession session, @PathVariable("_id") String _id) {
        ModelAndView view = new ModelAndView("/user/detail");
        view.addObject("_id", _id);
        return view;
    }

    @RequestMapping(value = "client/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView clientList() {
        ModelAndView mav = new ModelAndView("/client/list");
        return mav;
    }

    @RequestMapping(value = "client/new", method = RequestMethod.GET)
    public ModelAndView clientNew(HttpSession session) {
        ModelAndView view = new ModelAndView("/client/detail");
        return view;
    }

    @RequestMapping(value = "client/{_id}/edit", method = RequestMethod.GET)
    public ModelAndView clientEdit(HttpSession session, @PathVariable("_id") String _id) {
        ModelAndView view = new ModelAndView("/client/detail");
        view.addObject("_id", _id);
        return view;
    }

    @RequestMapping(value = "scope/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView scopeList() {
        ModelAndView mav = new ModelAndView("/scope/list");
        return mav;
    }

    @RequestMapping(value = "scope/new", method = RequestMethod.GET)
    public ModelAndView scopeNew(HttpSession session) {
        ModelAndView view = new ModelAndView("/scope/detail");
        return view;
    }

    @RequestMapping(value = "scope/{_id}/edit", method = RequestMethod.GET)
    public ModelAndView scopeEdit(HttpSession session, @PathVariable("_id") String _id) {
        ModelAndView view = new ModelAndView("/scope/detail");
        view.addObject("_id", _id);
        return view;
    }

    @RequestMapping(value = "management/custom", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView managementCustom() {
        ModelAndView mav = new ModelAndView("/management/custom");
        return mav;
    }
}
