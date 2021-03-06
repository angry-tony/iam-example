package org.opencloudengine.garuda.web.oauth;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.common.rest.Response;
import org.opencloudengine.garuda.util.*;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.console.oauthuser.OauthScopeToken;
import org.opencloudengine.garuda.web.console.oauthuser.OauthSessionToken;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OauthScopeService oauthScopeService;


    @RequestMapping(value = "/check_session", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response check_session(@RequestBody Map params) {
        Response response = new Response();
        try {

            //토큰값
            String token = (String) params.get("token");

            OauthSessionToken oauthSessionToken = oauthUserService.validateSessionToken(token);
            Map<String, Object> objectMap = JsonUtils.convertClassToMap(oauthSessionToken);

            response.getMap().putAll(objectMap);
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.getError().setMessage(ex.getMessage());
            if (ex.getCause() != null) response.getError().setCause(ex.getCause().getMessage());
            response.getError().setException(ExceptionUtils.getFullStackTrace(ex));
        }
        return response;
    }

    @RequestMapping(value = "/check_scope", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response check_scope(@RequestBody Map params) {
        Response response = new Response();
        try {

            //토큰값
            String token = (String) params.get("token");

            OauthScopeToken oauthScopeToken = oauthUserService.validateScopeToken(token);
            Map<String, Object> objectMap = JsonUtils.convertClassToMap(oauthScopeToken);

            response.getMap().putAll(objectMap);
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.getError().setMessage(ex.getMessage());
            if (ex.getCause() != null) response.getError().setCause(ex.getCause().getMessage());
            response.getError().setException(ExceptionUtils.getFullStackTrace(ex));
        }
        return response;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response login(@RequestBody Map params) {
        Response response = new Response();
        try {

            String managementKey = (String) params.get("managementKey");
            String userName = (String) params.get("userName");
            String userPassword = (String) params.get("userPassword");
            OauthSessionToken oauthSessionToken = oauthUserService.generateSessionToken(managementKey, userName, userPassword);

            if (oauthSessionToken == null) {
                response.setSuccess(false);
            } else {
                response.setSuccess(true);
                response.getMap().putAll(JsonUtils.convertClassToMap(oauthSessionToken));
            }
        } catch (Exception ex) {
            response.setSuccess(false);
            response.getError().setMessage(ex.getMessage());
            if (ex.getCause() != null) response.getError().setCause(ex.getCause().getMessage());
            response.getError().setException(ExceptionUtils.getFullStackTrace(ex));
        }
        return response;
    }

    @RequestMapping(value = "/create_scope_token", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response createScopeToken(@RequestBody Map params) {
        Response response = new Response();
        try {

            String managementKey = (String) params.get("managementKey");
            String clientKey = (String) params.get("clientKey");
            String userName = (String) params.get("userName");
            String scopes = (String) params.get("scopes");
            OauthScopeToken oauthScopeToken = oauthUserService.generateScopeToken(managementKey, userName, clientKey, scopes);

            if (oauthScopeToken == null) {
                response.setSuccess(false);
            } else {
                response.setSuccess(true);
                response.getMap().putAll(JsonUtils.convertClassToMap(oauthScopeToken));
            }
        } catch (Exception ex) {
            response.setSuccess(false);
            response.getError().setMessage(ex.getMessage());
            if (ex.getCause() != null) response.getError().setCause(ex.getCause().getMessage());
            response.getError().setException(ExceptionUtils.getFullStackTrace(ex));
        }
        return response;
    }


    /**
     * Authorization Code,Implicit Grant 플로우의 3th 파티 사용자가 로그인 페이지에서 인증절차를 마친 후 리다이렉트 되는 페이지
     *
     * @param request
     * @param response
     * @param managementKey
     * @param clientKey
     * @param userName
     * @param scopes
     * @param responseType
     * @param redirectUri
     * @param state
     * @param tokenType
     * @param claim
     * @throws IOException
     */
    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void redirect(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(defaultValue = "") String managementKey,
                         @RequestParam(defaultValue = "") String clientKey,
                         @RequestParam(defaultValue = "") String userName,
                         @RequestParam(defaultValue = "") String scopes,
                         @RequestParam(defaultValue = "") String responseType,
                         @RequestParam(defaultValue = "") String redirectUri,
                         @RequestParam(defaultValue = "") String state,
                         @RequestParam(defaultValue = "") String tokenType,
                         @RequestParam(defaultValue = "") String claim
    ) throws IOException {
        try {
            AuthorizeResponse authorizeResponse = new AuthorizeResponse();

            authorizeResponse.setClientId(clientKey);
            authorizeResponse.setRedirectUri(redirectUri);
            authorizeResponse.setScope(scopes);
            authorizeResponse.setState(state);
            authorizeResponse.setResponseType(responseType);
            authorizeResponse.setTokenType(tokenType);
            authorizeResponse.setClaim(URLDecoder.decode(claim));
            authorizeResponse.setOauthClient(oauthClientService.selectByClientKey(clientKey));
            authorizeResponse.setOauthScopes(oauthScopeService.selectClientScopes(authorizeResponse.getOauthClient().get_id()));
            authorizeResponse.setManagement(managementService.selectByKey(managementKey));
            authorizeResponse.setOauthUser(oauthUserService.selectByManagementIdAndUserName(authorizeResponse.getManagement().get_id(), userName));
            oauthService.processAuthorize(authorizeResponse, response);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
        }
    }

    /**
     * Authorization Code,Implicit Grant 플로우의 3th 파티 앱이 최초 요청하는 로그인 페이지.
     *
     * @param session
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView authorize(HttpSession session, HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        try {
            AuthorizeResponse authorizeResponse = oauthService.validateAuthorize(request);
            if (authorizeResponse.getError() != null) {

                //응답에 바로 에러를 보내준다.
                Map map = new HashMap();
                map.put("error", authorizeResponse.getError());
                map.put("error_description", authorizeResponse.getError_description());
                map.put("state", authorizeResponse.getState());
                String marshal = JsonUtils.marshal(map);
                String prettyPrint = JsonFormatterUtils.prettyPrint(marshal);
                response.getWriter().write(prettyPrint);

                //TODO 아래의 리다이렉트 URL 로 에러를 보내주는 로직은, 추후 큐에 의해 스케쥴링되도록 한다.
                //리다이렉트 URL 에도 에러를 보내준다.
                //리다이렉트 URL 이 없는 경우는 실행하지 않는다.
                if (!StringUtils.isEmpty(authorizeResponse.getRedirectUri())) {
                    try {
                        //application/x-www-form-urlencoded 을 이용해 리다이렉트 할 것.
                        Map<String, String> headers = new HashMap();
                        headers.put("Content-Type", "application/x-www-form-urlencoded");

                        String getQueryString = HttpUtils.createGETQueryString(map);
                        String url = authorizeResponse.getRedirectUri();
                        new HttpUtils().makeRequest("GET", url + getQueryString, null, headers);
                    } catch (Exception ex) {
                        //리다이렉트 전달 과정 중 실패가 일어나더라도 프로세스에는 영향을 끼지지 않는다.
                    }
                }
                return null;
            } else {
                //인증 화면으로 넘어갈 것.
                ModelAndView mav = new ModelAndView("/auth/oauth-login");
                mav.addObject("authorizeResponse", authorizeResponse);
                mav.addObject("jsonAuthorizeResponse", JsonUtils.marshal(authorizeResponse));
                return mav;
            }
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/access_token", method = RequestMethod.POST, produces = "application/json")
    public void accessToken(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        try {
            oauthService.processAccessToken(request, response);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
        }
    }

    @RequestMapping(value = "/token_info", method = RequestMethod.GET, produces = "application/json")
    public void tokenInfo(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        try {
            oauthService.processTokenInfo(request, response);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
        }
    }
}
