package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.couchdb.CouchDAO;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthRegiste extends CouchDAO{

    private String clientId;
    private String requestType;
    private String token;
    private String redirect_url;
    private String lang;
    private OauthUser oauthUser;
    private Long regDate;
    private Long updDate;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public OauthUser getOauthUser() {
        return oauthUser;
    }

    public void setOauthUser(OauthUser oauthUser) {
        this.oauthUser = oauthUser;
    }

    public Long getRegDate() {
        return regDate;
    }

    public void setRegDate(Long regDate) {
        this.regDate = regDate;
    }

    public Long getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Long updDate) {
        this.updDate = updDate;
    }
}
