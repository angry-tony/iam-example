package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.common.security.SessionUtils;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
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
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/console/user")
public class OauthUserController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private UserService userService;

    @Autowired
    private OauthUserService oauthUserService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView list(HttpSession session) {
        Management management = (Management) session.getAttribute("management");

        List<OauthUser> oauthUsers = oauthUserService.selectByManagementId(management.get_id());
        ModelAndView mav = new ModelAndView("/console/user/list");

        mav.addObject("management", management);
        mav.addObject("oauthUsers", oauthUsers);
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView newManagement(HttpSession session) {
        Management management = (Management) session.getAttribute("management");

        ModelAndView mav = new ModelAndView("/console/user/new");

        mav.addObject("management", management);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView create(HttpSession session,
                               @RequestParam(defaultValue = "") String body
    ) throws IOException {
        Management management = (Management) session.getAttribute("management");

        try {
            Map map = JsonUtils.unmarshal(body);
            OauthUser oauthUser = new OauthUser();
            oauthUser.putAll(map);

            //같은 유저 검색
            OauthUser existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), oauthUser.getUserName());
            if (existUser != null) {
                ModelAndView mav = new ModelAndView("/console/user/new");
                mav.addObject("duplicate", true);
                return mav;
            }

            //유저 생성
            oauthUserService.createUser(management.get_id(), oauthUser);

            //리스트 페이지 반환
            List<OauthUser> oauthUsers = oauthUserService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/user/list");
            mav.addObject("management", management);
            mav.addObject("oauthUsers", oauthUsers);
            return mav;

        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/user/new");
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
            //유저 검색
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthUser == null) {
                throw new ServiceException("Invalid oauth user id");
            }

            ModelAndView mav = new ModelAndView("/console/user/edit");
            mav.addObject("management", management);
            mav.addObject("oauthUser", oauthUser);

            Map<String, Object> map = JsonUtils.convertClassToMap(oauthUser);
            map.remove("_id");
            map.remove("_rev");
            map.remove("docType");
            map.remove("managementId");
            map.remove("userPassword");
            map.remove("regDate");
            map.remove("updDate");
            mav.addObject("oauthUserJson", JsonUtils.marshal(map));
            return mav;
        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth user id");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView delete(HttpSession session,
                               @RequestParam(defaultValue = "") String _id) throws IOException {

        Management management = (Management) session.getAttribute("management");

        try {
            //유저 검색
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthUser == null) {
                throw new ServiceException("Invalid oauth user id");
            }

            oauthUserService.deleteById(_id);

            List<OauthUser> oauthUsers = oauthUserService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/user/list");
            mav.addObject("management", management);
            mav.addObject("oauthUsers", oauthUsers);
            return mav;

        } catch (Exception ex) {
            throw new ServiceException("Invalid oauth user id");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView update(HttpSession session,
                               @RequestParam(defaultValue = "") String _id,
                               @RequestParam(defaultValue = "") String body) throws IOException {

        Management management = (Management) session.getAttribute("management");

        try {
            //유저 객체
            Map map = JsonUtils.unmarshal(body);
            OauthUser updateUser = new OauthUser();
            updateUser.putAll(map);
            updateUser.set_id(_id);

            //유저 검색
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndId(management.get_id(), updateUser.get_id());
            if (oauthUser == null) {
                throw new ServiceException("Invalid oauth user id");
            }

            //같은 이름 유저 검색
            OauthUser existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), updateUser.getUserName());
            if (existUser != null) {
                if (!existUser.get_id().equals(updateUser.get_id())) {
                    ModelAndView mav = new ModelAndView("/console/user/edit");
                    mav.addObject("management", management);
                    mav.addObject("oauthUser", oauthUser);
                    mav.addObject("duplicate", true);
                    return mav;
                }
            }
            oauthUserService.updateById(updateUser);

            List<OauthUser> oauthUsers = oauthUserService.selectByManagementId(management.get_id());
            ModelAndView mav = new ModelAndView("/console/user/list");
            mav.addObject("management", management);
            mav.addObject("oauthUsers", oauthUsers);
            return mav;
        } catch (Exception ex) {
            ModelAndView mav = new ModelAndView("/console/user/edit");
            mav.addObject("management", management);
            mav.addObject("failed", true);
            return mav;
        }
    }
}
