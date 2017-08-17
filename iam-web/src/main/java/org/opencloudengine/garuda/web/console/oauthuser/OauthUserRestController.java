package org.opencloudengine.garuda.web.console.oauthuser;

import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.proxy.ProxyRequest;
import org.opencloudengine.garuda.proxy.ProxyService;
import org.opencloudengine.garuda.util.ExceptionUtils;
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
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private ProxyService proxyService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> listAllUsers(HttpServletRequest request, HttpServletResponse response) {
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
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/search/findByName", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthUser> getUserByName(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam("userName") String name) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), name);
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/pagination", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> pagination(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "100") int limit) {

        try {
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
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/search/{searchKey}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthUser>> search(HttpServletRequest request, HttpServletResponse response,
                                                  @PathVariable("searchKey") String searchKey,
                                                  @RequestParam(defaultValue = "0") int offset,
                                                  @RequestParam(defaultValue = "100") int limit) {

        try {
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
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthUser> getUser(HttpServletRequest request, HttpServletResponse response,
                                             @PathVariable("_id") String _id) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser oauthUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(HttpServletRequest request, HttpServletResponse response,
                                           @RequestBody OauthUser oauthUser, UriComponentsBuilder ucBuilder) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser existUser = oauthUserService.selectByName(oauthUser.getUserName());
            if (existUser != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthUser createdUser = oauthUserService.createUser(management.get_id(), oauthUser);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/user/{_id}").buildAndExpand(createdUser.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthUser> updateUser(HttpServletRequest request, HttpServletResponse response,
                                                @PathVariable("_id") String _id, @RequestBody OauthUser oauthUser) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            //같은 사용자 이름으로는 업데이트 불가.
            if(oauthUser.getUserName() != null){
                OauthUser existUser = oauthUserService.selectByName(oauthUser.getUserName());
                if (existUser != null && !existUser.get_id().equals(currentUser.get_id())) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }

            oauthUser.set_id(currentUser.get_id());
            oauthUserService.updateById(oauthUser);

            currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthUser> deleteUser(HttpServletRequest request, HttpServletResponse response,
                                                @PathVariable("_id") String _id) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser currentUser = oauthUserService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUserService.deleteById(currentUser.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ResponseEntity<?> createUserAvatar(HttpServletRequest request, HttpServletResponse response,
                                                 @RequestParam(required = false) String id,
                                                 @RequestParam(required = false) String userName,
                                                 @RequestParam("contentType") String contentType,
                                                 UriComponentsBuilder ucBuilder) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser existUser = null;
            //id 가 있으면 id 로 검색
            if (!StringUtils.isEmpty(id)) {
                existUser = oauthUserService.selectByManagementIdAndId(management.get_id(), id);
            }
            //userName 이 있으면 userName 으로 검색
            else if (!StringUtils.isEmpty(userName)) {
                existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), userName);
            }
            if (existUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (!contentType.startsWith("image/")) {
                return new ResponseEntity<>("Invalid Mime Type", HttpStatus.BAD_REQUEST);
            }

            oauthUserService.insertAvatar(request.getInputStream(), contentType, existUser);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/user/{_id}").buildAndExpand(existUser.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/avatar/formdata", method = RequestMethod.POST)
    public ResponseEntity<?> createUserAvatarByFormData(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam(required = false) String id,
                                                        @RequestParam(required = false) String userName,
                                                        @RequestParam("contentType") String contentType,
                                                        @RequestParam("file") MultipartFile file,
                                                        UriComponentsBuilder ucBuilder) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser existUser = null;
            //id 가 있으면 id 로 검색
            if (!StringUtils.isEmpty(id)) {
                existUser = oauthUserService.selectByManagementIdAndId(management.get_id(), id);
            }
            //userName 이 있으면 userName 으로 검색
            else if (!StringUtils.isEmpty(userName)) {
                existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), userName);
            }
            if (existUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUserService.insertAvatar(file.getInputStream(), contentType, existUser);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/user/{_id}").buildAndExpand(existUser.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.DELETE)
    public ResponseEntity<OauthUser> deleteUser(HttpServletRequest request, HttpServletResponse response,
                                                @RequestParam(required = false) String id,
                                                @RequestParam(required = false) String userName) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser existUser = null;
            //id 가 있으면 id 로 검색
            if (!StringUtils.isEmpty(id)) {
                existUser = oauthUserService.selectByManagementIdAndId(management.get_id(), id);
            }
            //userName 이 있으면 userName 으로 검색
            else if (!StringUtils.isEmpty(userName)) {
                existUser = oauthUserService.selectByManagementIdAndUserName(management.get_id(), userName);
            }
            if (existUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            oauthUserService.deleteAvatar(existUser);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Void> getUser(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false) String id,
                                        @RequestParam(required = false) String userName) {

        try {
            OauthUser existUser = null;
            //id 가 있으면 id 로 검색
            if (!StringUtils.isEmpty(id)) {
                existUser = oauthUserService.selectById(id);
            }
            //userName 이 있으면 userName 으로 검색
            else if (!StringUtils.isEmpty(userName)) {
                existUser = oauthUserService.selectByName(userName);
            }
            if (existUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String dbUrl = config.getProperty("couch.db.url");
            String dbName = config.getProperty("couch.db.database");

            ProxyRequest proxyRequest = new ProxyRequest();
            proxyRequest.setRequest(request);
            proxyRequest.setResponse(response);

            proxyRequest.setHost(dbUrl);
            proxyRequest.setPath("/" + dbName + "/" + existUser.get_id() + "/avatar");
            proxyRequest.setHeaders(new HashMap<String, String>());
            proxyService.doProxy(proxyRequest);
            return null;

        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
