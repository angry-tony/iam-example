package org.opencloudengine.garuda.web.oauth;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthAccessToken implements Serializable {

    private Long id;
    private String type;
    private String scopes;
    private String token;
    private Long oauthUserId;
    private Long groupId;
    private Long clientId;
    private String refreshToken;
    private String additionalInformation;
    private Timestamp regDate;
    private Timestamp updDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getOauthUserId() {
        return oauthUserId;
    }

    public void setOauthUserId(Long oauthUserId) {
        this.oauthUserId = oauthUserId;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
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
        return "OauthAccessToken{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", scopes='" + scopes + '\'' +
                ", token='" + token + '\'' +
                ", oauthUserId=" + oauthUserId +
                ", groupId=" + groupId +
                ", clientId=" + clientId +
                ", refreshToken='" + refreshToken + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
