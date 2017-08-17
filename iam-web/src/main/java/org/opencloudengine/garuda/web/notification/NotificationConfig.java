package org.opencloudengine.garuda.web.notification;

import org.opencloudengine.garuda.couchdb.CouchDAO;


/**
 * Created by uengine on 2017. 1. 25..
 */
public class NotificationConfig extends CouchDAO{

    private String clientId;
    private String configuration;
    private Long regDate;
    private Long updDate;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
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
