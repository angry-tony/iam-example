package org.opencloudengine.garuda.web.console.oauthregiste;

import org.apache.commons.lang.StringUtils;
import org.opencloudengine.garuda.proxy.ProxyRequest;
import org.opencloudengine.garuda.proxy.ProxyService;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.notification.NotificationType;
import org.opencloudengine.garuda.web.rest.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/rest/v1")
public class OauthRegisteRestController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private ProxyService proxyService;

    @Autowired
    private OauthRegisteService registeService;

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity<OauthRegiste> signUpUser(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam("redirect_url") String redirect_url,
                                                   @RequestBody OauthUser oauthUser) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser existUser = oauthUserService.selectByName(oauthUser.getUserName());
            if (existUser != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthRegiste oauthRegiste = registeService.singUp(oauthClient, oauthUser, redirect_url);

            return new ResponseEntity<>(oauthRegiste, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/signup/verification", method = RequestMethod.GET)
    public ResponseEntity<OauthUser> signUpVerification(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam("token") String token) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser oauthUser = registeService.verification(oauthClient, token, NotificationType.SIGN_UP.toString());
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/signup/accept", method = RequestMethod.POST)
    public ResponseEntity<OauthUser> signUpAccept(HttpServletRequest request, HttpServletResponse response,
                                                  @RequestBody Map params) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser oauthUser = registeService.acceptSingUp(oauthClient, params.get("token").toString());
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(oauthUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/repassword", method = RequestMethod.PUT)
    public ResponseEntity<OauthUser> repassword(HttpServletRequest request, HttpServletResponse response,
                                                @RequestBody Map params) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser oauthUser = oauthUserService.selectByManagementIdAndUserName(oauthClient.getManagementId(), params.get("userName").toString());
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthUser = registeService.rePassword(oauthClient, oauthUser, params.get("beforePassword").toString(), params.get("afterPassword").toString());
            return new ResponseEntity<>(oauthUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/forgot", method = RequestMethod.POST)
    public ResponseEntity<OauthRegiste> forgotPassword(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam("redirect_url") String redirect_url,
                                                   @RequestBody OauthUser oauthUser) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser existUser = oauthUserService.selectByName(oauthUser.getUserName());
            if (existUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            OauthRegiste oauthRegiste = registeService.forgotPassword(oauthClient, existUser, redirect_url);

            return new ResponseEntity<>(oauthRegiste, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/forgot/verification", method = RequestMethod.GET)
    public ResponseEntity<OauthUser> forgotPasswordVerification(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam("token") String token) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthUser oauthUser = registeService.verification(oauthClient, token, NotificationType.FORGOT_PASSWORD.toString());
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(oauthUser, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/user/forgot/accept", method = RequestMethod.POST)
    public ResponseEntity<OauthUser> forgotPasswordAccept(HttpServletRequest request, HttpServletResponse response,
                                                  @RequestBody Map params) {
        try {
            OauthClient oauthClient = restAuthService.clientParser(request);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthUser oauthUser = registeService.acceptPassword(oauthClient, params.get("token").toString(),params.get("password").toString());
            if (oauthUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(oauthUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
