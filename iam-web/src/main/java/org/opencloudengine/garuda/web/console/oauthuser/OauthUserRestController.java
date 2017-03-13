package org.opencloudengine.garuda.web.console.oauthuser;

import net.minidev.json.JSONObject;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.rest.RestAuthService;
import org.opencloudengine.garuda.web.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/rest/v1")
public class OauthUserRestController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private RestAuthService restAuthService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> listAllUsers(HttpServletRequest request) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            List<OauthUser> users = oauthUserService.selectAllByManagementId(management.get_id());
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/pagination", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> pagination(HttpServletRequest request,
                                                      @RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "100") int limit) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //총합
        Long max = oauthUserService.countAllByManagementId(management.get_id());

        //컨디션 합
        Long total = max;

        //컨디션 결과
        List<OauthUser> oauthUsers = oauthUserService.selectByManagementId(management.get_id(), limit, new Long(offset));

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-uengine-pagination-maxnbrecords", max + "");
        headers.add("x-uengine-pagination-currentoffset", offset + "");
        headers.add("x-uengine-pagination-totalnbrecords", total + "");

        return new ResponseEntity<>(oauthUsers, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/search/{searchKey}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> search(HttpServletRequest request,
                                                      @PathVariable("searchKey") String searchKey,
                                                      @RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "100") int limit) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //총합
        Long max = oauthUserService.countAllByManagementId(management.get_id());

        //컨디션 합
        Long total = oauthUserService.countAllByManagementIdLikeUserName(management.get_id(), searchKey);

        //컨디션 결과
        List<OauthUser> oauthUsers = oauthUserService.selectByManagementLikeUserName(management.get_id(), searchKey, limit, new Long(offset));

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-uengine-pagination-maxnbrecords", max + "");
        headers.add("x-uengine-pagination-currentoffset", offset + "");
        headers.add("x-uengine-pagination-totalnbrecords", total + "");

        return new ResponseEntity<>(oauthUsers, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthUser> getUser(HttpServletRequest request, @PathVariable("_id") String _id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/username/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthUser> getUserByName(HttpServletRequest request, @PathVariable("name") String name) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), name);
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(HttpServletRequest request, @RequestBody OauthUser oauthUser, UriComponentsBuilder ucBuilder) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthUser existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), oauthUser.getUserName());
            if (existUser != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthUser createdUser = oauthUserService.createUser(management.get_id(), oauthUser);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/user/{_id}").buildAndExpand(createdUser.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthUser> updateUser(HttpServletRequest request, @PathVariable("_id") String _id, @RequestBody OauthUser oauthUser) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthUser currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUser.set_id(currentUser.get_id());
            oauthUserService.updateById(oauthUser);

            currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthUser> deleteUser(HttpServletRequest request, @PathVariable("_id") String _id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthUser currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUserService.deleteById(currentUser.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
