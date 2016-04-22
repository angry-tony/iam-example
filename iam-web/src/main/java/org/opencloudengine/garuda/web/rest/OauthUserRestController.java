package org.opencloudengine.garuda.web.rest;


import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.opencloudengine.garuda.web.management.Management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
            List<OauthUser> users = oauthUserService.selectByGroupId(management.getId());
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthUser> getUser(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthUser oauthUser = oauthUserService.selectByGroupIdAndId(management.getId(), id);
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

            OauthUser existUser = oauthUserService.selectByGroupIdAndUserName(management.getId(), oauthUser.getUserName());
            if (existUser != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthUser createdUser = oauthUserService.createUser(management.getId(), oauthUser.getUserName(), oauthUser.getUserPassword(), oauthUser.getLevel(), oauthUser.getAdditionalInformation());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/user/{id}").buildAndExpand(createdUser.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthUser> updateUser(HttpServletRequest request, @PathVariable("id") long id, @RequestBody OauthUser oauthUser) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthUser currentUser = oauthUserService.selectByGroupIdAndId(management.getId(), id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUser.setId(currentUser.getId());
            oauthUserService.updateById(oauthUser.getId(),oauthUser.getUserName(),oauthUser.getUserPassword(),oauthUser.getLevel(),oauthUser.getAdditionalInformation());
            //oauthUserService.updateById(oauthUser);

            currentUser = oauthUserService.selectByGroupIdAndId(management.getId(), id);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthUser> deleteUser(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthUser currentUser = oauthUserService.selectByGroupIdAndId(management.getId(), id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUserService.deleteById(currentUser.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/search/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> searchUser(HttpServletRequest request,
                                                @RequestParam(required = false) String userName,
                                                @RequestParam(required = false) String userPassword,
                                                @RequestParam(required = false) Integer level,
                                                @RequestParam(required = false) String additionalInformation) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthUser oauthUser = new OauthUser();
            oauthUser.setGroupId(management.getId());
            oauthUser.setUserName(userName);
            oauthUser.setUserPassword(userPassword);
            oauthUser.setLevel(level);
            oauthUser.setAdditionalInformation(additionalInformation);

            List<OauthUser> users = oauthUserService.selectByCondition(oauthUser);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
