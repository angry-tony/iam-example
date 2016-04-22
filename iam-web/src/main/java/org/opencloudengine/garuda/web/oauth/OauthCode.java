package org.opencloudengine.garuda.web.oauth;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by cloudine on 2015. 6. 3..
 */
public class OauthCode implements Serializable {

    private Long id;
    private Long groupId;
    private Long clientId;
    private Long oauthUserId;
    private String code;
    private String scopes;
    private Timestamp regDate;
    private Timestamp updDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getOauthUserId() {
        return oauthUserId;
    }

    public void setOauthUserId(Long oauthUserId) {
        this.oauthUserId = oauthUserId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Timestamp updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", clientId=" + clientId +
                ", oauthUserId=" + oauthUserId +
                ", code='" + code + '\'' +
                ", scopes='" + scopes + '\'' +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
