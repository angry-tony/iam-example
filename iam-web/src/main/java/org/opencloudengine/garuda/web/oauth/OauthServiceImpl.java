package org.opencloudengine.garuda.web.oauth;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.HttpUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientRepository;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
public class OauthServiceImpl implements OauthService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OauthScopeService oauthScopeService;

    @Autowired
    ConfigurationHelper configurationHelper;

    private String INVALID_REQUEST = "invalid_request";
    private String UNAUTHORIZED_CLIENT = "unauthorized_client";
    private String ACCESS_DENIED = "access_denied";
    private String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
    private String INVALID_SCOPE = "invalid_scope";
    private String SERVER_ERROR = "server_error";
    private String TEMPORARILY_UNAVAILABLE = "temporarily_unavailable";

    @Override
    public AuthorizeResponse validateAuthorize(HttpServletRequest request) {

        AuthorizeResponse authorizeResponse = new AuthorizeResponse();

        authorizeResponse.setClientId(request.getParameter("client_id"));
        authorizeResponse.setResponseType(request.getParameter("response_type"));
        authorizeResponse.setRedirectUri(request.getParameter("redirect_uri"));
        authorizeResponse.setScope(request.getParameter("scope"));
        authorizeResponse.setState(request.getParameter("state"));

        //필요한 값을 검증한다.
        if (StringUtils.isEmpty(authorizeResponse.getClientId())) {
            authorizeResponse.setError(this.INVALID_REQUEST);
            authorizeResponse.setError_description("client_id is required.");
            return authorizeResponse;
        }
        if (StringUtils.isEmpty(authorizeResponse.getResponseType())) {
            authorizeResponse.setError(this.INVALID_REQUEST);
            authorizeResponse.setError_description("response_type is required.");
            return authorizeResponse;
        }
        if (StringUtils.isEmpty(authorizeResponse.getScope())) {
            authorizeResponse.setError(this.INVALID_REQUEST);
            authorizeResponse.setError_description("scope is required.");
            return authorizeResponse;
        }

        //클라이언트를 얻는다
        OauthClient oauthClient = oauthClientService.selectByClientKey(authorizeResponse.getClientId());
        if (oauthClient == null) {
            authorizeResponse.setError(this.UNAUTHORIZED_CLIENT);
            authorizeResponse.setError_description("Requested client is not exist.");
            return authorizeResponse;
        }
        authorizeResponse.setOauthClient(oauthClient);

        //클라이언트의 리스폰트 타입 허용 범위를 체크한다.
        String responseType = authorizeResponse.getResponseType();
        List grantTypes = Arrays.asList(oauthClient.getAuthorizedGrantTypes().split(","));
        switch (responseType) {
            case "code":
                if (!grantTypes.contains("code")) {
                    authorizeResponse.setError(this.UNSUPPORTED_RESPONSE_TYPE);
                    authorizeResponse.setError_description("Requested client does not support response_type code");
                    return authorizeResponse;
                }
                break;
            case "token":
                if (!grantTypes.contains("implicit")) {
                    authorizeResponse.setError(this.UNSUPPORTED_RESPONSE_TYPE);
                    authorizeResponse.setError_description("Requested client does not support response_type token");
                    return authorizeResponse;
                }
                break;
            default:
                authorizeResponse.setError(this.UNSUPPORTED_RESPONSE_TYPE);
                authorizeResponse.setError_description("response_type must be code or token");
                return authorizeResponse;
        }

        //리다이렉트 유알엘을 검증한다.
        if (StringUtils.isEmpty(authorizeResponse.getRedirectUri())) {
            authorizeResponse.setRedirectUri(oauthClient.getWebServerRedirectUri());
        }
        if (StringUtils.isEmpty(authorizeResponse.getRedirectUri())) {
            authorizeResponse.setError(this.UNSUPPORTED_RESPONSE_TYPE);
            authorizeResponse.setError_description("Requested client does not have default redirect_uri. You must set redirect_uri in your parameters.");
            return authorizeResponse;
        }

        //스코프를 검증한다.
        List<OauthScope> clientScopes = oauthScopeService.selectClientScopes(oauthClient.getId());
        List<OauthScope> requestScopes = new ArrayList<OauthScope>();
        List<String> enabelScopesNames = new ArrayList<>();
        for (int i = 0; i < clientScopes.size(); i++) {
            enabelScopesNames.add(clientScopes.get(i).getName());
        }
        List<String> requestScopesNames = Arrays.asList(authorizeResponse.getScope().split(","));
        for (int i = 0; i < requestScopesNames.size(); i++) {
            if (!enabelScopesNames.contains(requestScopesNames.get(i))) {
                authorizeResponse.setError(this.INVALID_SCOPE);
                authorizeResponse.setError_description("Client dost not have requested scope");
                return authorizeResponse;
            } else {
                for (int c = 0; c < clientScopes.size(); c++) {
                    if (clientScopes.get(c).getName().equals(requestScopesNames.get(i))) {
                        requestScopes.add(clientScopes.get(c));
                    }
                }
            }
        }
        authorizeResponse.setOauthScopes(requestScopes);

        return authorizeResponse;
    }

    @Override
    public void redirectAuthorize(AuthorizeResponse authorizeResponse) {

        //리다이렉트 URL 이 없는 경우는 실행하지 않는다.
        if (StringUtils.isEmpty(authorizeResponse.getRedirectUri())) {
            return;
        }

        //application/x-www-form-urlencoded 을 이용해 리다이렉트 할 것.
        //에러, 성공 둘 다.

        try {
            //에러를 전달해야 하는경우
            if (authorizeResponse.getError() != null) {

                Map<String, String> headers = new HashMap();
                headers.put("Content-Type", "application/x-www-form-urlencoded");

                Map params = new HashMap();
                params.put("error", authorizeResponse.getError());
                params.put("error_description", authorizeResponse.getError_description());
                params.put("state", authorizeResponse.getState());

                String getQueryString = HttpUtils.createGETQueryString(params);
                String url = authorizeResponse.getRedirectUri();

                HttpResponse response = new HttpUtils().makeRequest("GET", url+getQueryString, null, headers);

                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                if (statusCode != 200) {
                    throw new ServiceException("statusCode" + statusCode + ", while excute" + url);
                }
            }
        } catch (Exception ex) {
            //리다이렉트 전달 과정 중 실패가 일어나더라도 프로세스에는 영향을 끼지지 않는다.
        }
    }
}
