package org.opencloudengine.garuda.web.management;


import com.google.common.base.Joiner;
import net.minidev.json.JSONObject;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.common.rest.Response;
import org.opencloudengine.garuda.common.security.SessionUtils;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.oauth.CustomService;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/rest/v1")
public class ManagementRestController {

    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private CustomService customService;

    @RequestMapping(value = "/management", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Management>> getManagements(HttpServletRequest request) {
        try {
            User user = restAuthService.userParser(request);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<Management> managements = managementService.selectAllByUserId(user.get_id());
            if (managements == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(managements, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/management/{_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Management> getManagement(HttpServletRequest request, @PathVariable("_id") String _id) {

        try {
            User user = restAuthService.userParser(request);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Management management = managementService.selectByUserIdAndId(user.get_id(), _id);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(management, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/management", method = RequestMethod.POST)
    public ResponseEntity<Void> createManagement(HttpServletRequest request, @RequestBody Management management, UriComponentsBuilder ucBuilder) {

        try {
            User user = restAuthService.userParser(request);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Management created = managementService.createManagement(
                    user.get_id(), management.getManagementName(),
                    management.getDescription(),
                    management.getSessionTokenLifetime(),
                    management.getScopeCheckLifetime());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/management/{_id}").buildAndExpand(created.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/management/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<Management> updateManagement(HttpServletRequest request, @PathVariable("_id") String _id, @RequestBody Management management) {

        try {
            User user = restAuthService.userParser(request);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Management currentManagement = managementService.selectByUserIdAndId(user.get_id(), _id);
            if (currentManagement == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            management.set_id(currentManagement.get_id());
            managementService.updateById(management);

            currentManagement = managementService.selectByUserIdAndId(user.get_id(), _id);
            return new ResponseEntity<>(currentManagement, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/management/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthClient> deleteManagement(HttpServletRequest request, @PathVariable("_id") String _id) {

        try {
            User user = restAuthService.userParser(request);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Management currentManagement = managementService.selectByUserIdAndId(user.get_id(), _id);
            if (currentManagement == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            managementService.deleteById(currentManagement.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/management/{_id}/test", method = RequestMethod.POST)
    public ResponseEntity<Map> testScript(HttpServletRequest request, @PathVariable("_id") String _id, @RequestBody Map params) {
        try {
            Management management = managementService.selectById(_id);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            String userId = (String) params.get("userId");
            String clientId = (String) params.get("clientId");
            List<String> list = (List) params.get("scopes");

            String tokenType = (String) params.get("tokenType");
            String claim = (String) params.get("claim");
            String script = (String) params.get("script");

            Map map = customService.processTokenTest(management, userId, clientId, Joiner.on(",").join(list), tokenType, claim, script);
            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
