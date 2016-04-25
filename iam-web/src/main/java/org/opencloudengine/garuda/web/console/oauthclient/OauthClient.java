package org.opencloudengine.garuda.web.console.oauthclient;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthClient implements Serializable {

    private Long id;
    private Long groupId;
    private String name;
    private String description;
    private String clientKey;
    private String clientSecret;
    private String clientJwtSecret;
    private String clientTrust;
    private String clientType;
    private String activeClient;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String refreshTokenValidity;
    private String additionalInformation;
    private Integer codeLifetime;
    private Integer refreshTokenLifetime;
    private Integer accessTokenLifetime;
    private Integer jwtTokenLifetime;
    private Date regDate;
    private Date updDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientJwtSecret() {
        return clientJwtSecret;
    }

    public void setClientJwtSecret(String clientJwtSecret) {
        this.clientJwtSecret = clientJwtSecret;
    }

    public String getClientTrust() {
        return clientTrust;
    }

    public void setClientTrust(String clientTrust) {
        this.clientTrust = clientTrust;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getActiveClient() {
        return activeClient;
    }

    public void setActiveClient(String activeClient) {
        this.activeClient = activeClient;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(String refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Integer getCodeLifetime() {
        return codeLifetime;
    }

    public void setCodeLifetime(Integer codeLifetime) {
        this.codeLifetime = codeLifetime;
    }

    public Integer getRefreshTokenLifetime() {
        return refreshTokenLifetime;
    }

    public void setRefreshTokenLifetime(Integer refreshTokenLifetime) {
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    public Integer getAccessTokenLifetime() {
        return accessTokenLifetime;
    }

    public void setAccessTokenLifetime(Integer accessTokenLifetime) {
        this.accessTokenLifetime = accessTokenLifetime;
    }

    public Integer getJwtTokenLifetime() {
        return jwtTokenLifetime;
    }

    public void setJwtTokenLifetime(Integer jwtTokenLifetime) {
        this.jwtTokenLifetime = jwtTokenLifetime;
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
        return "OauthClient{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", clientKey='" + clientKey + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", clientJwtSecret='" + clientJwtSecret + '\'' +
                ", clientTrust='" + clientTrust + '\'' +
                ", clientType='" + clientType + '\'' +
                ", activeClient='" + activeClient + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", refreshTokenValidity='" + refreshTokenValidity + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", codeLifetime=" + codeLifetime +
                ", refreshTokenLifetime=" + refreshTokenLifetime +
                ", accessTokenLifetime=" + accessTokenLifetime +
                ", jwtTokenLifetime=" + jwtTokenLifetime +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
