package org.opencloudengine.garuda.web.console.oauthregiste;

import org.opencloudengine.garuda.couchdb.CouchDAO;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthRegiste extends CouchDAO{

    private String clientId;
    private String notification_type;
    private String token;
    private String redirect_url;
    private OauthUser oauthUser;
    private Long regDate;
    private Long updDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
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
