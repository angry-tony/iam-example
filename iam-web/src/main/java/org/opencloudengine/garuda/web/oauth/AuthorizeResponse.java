package org.opencloudengine.garuda.web.oauth;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;

import java.io.Serializable;
import java.util.List;

/**
 * Created by uengine on 2016. 4. 15..
 */
public class AuthorizeResponse implements Serializable {
    private String clientId;
    private String responseType;
    private String redirectUri;
    private String scope;
    private String state;
    private String code;
    private OauthClient oauthClient;
    private List<OauthScope> oauthScopes;

    private String error;
    private String error_description;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OauthClient getOauthClient() {
        return oauthClient;
    }

    public void setOauthClient(OauthClient oauthClient) {
        this.oauthClient = oauthClient;
    }

    public List<OauthScope> getOauthScopes() {
        return oauthScopes;
    }

    public void setOauthScopes(List<OauthScope> oauthScopes) {
        this.oauthScopes = oauthScopes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        return "AuthorizeResponse{" +
                "clientId='" + clientId + '\'' +
                ", responseType='" + responseType + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", scope='" + scope + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                ", oauthClient=" + oauthClient +
                ", oauthScopes=" + oauthScopes +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }
}
