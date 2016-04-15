package org.opencloudengine.garuda.web.console.oauthclient;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by uengine on 2016. 4. 15..
 */
public class OauthClientScopes implements Serializable {

    private Long id;
    private Long clientId;
    private Long scopeId;
    private Date regDate;
    private Date updDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getScopeId() {
        return scopeId;
    }

    public void setScopeId(Long scopeId) {
        this.scopeId = scopeId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "OauthClientScopes{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", scopeId=" + scopeId +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
