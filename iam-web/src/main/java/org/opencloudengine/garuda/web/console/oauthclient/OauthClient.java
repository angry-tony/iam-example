package org.opencloudengine.garuda.web.console.oauthclient;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by cloudine on 2015. 6. 3..
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
    private boolean activeClient;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private boolean refreshTokenValidity;
    private String additionalInformation;
    private int codeLifetime;
    private int refreshTokenLifetime;
    private int accessTokenLifetime;
    private int jwtTokenLifetime;
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

    public boolean isActiveClient() {
        return activeClient;
    }

    public void setActiveClient(boolean activeClient) {
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

    public boolean isRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(boolean refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public int getCodeLifetime() {
        return codeLifetime;
    }

    public void setCodeLifetime(int codeLifetime) {
        this.codeLifetime = codeLifetime;
    }

    public int getRefreshTokenLifetime() {
        return refreshTokenLifetime;
    }

    public void setRefreshTokenLifetime(int refreshTokenLifetime) {
        this.refreshTokenLifetime = refreshTokenLifetime;
    }

    public int getAccessTokenLifetime() {
        return accessTokenLifetime;
    }

    public void setAccessTokenLifetime(int accessTokenLifetime) {
        this.accessTokenLifetime = accessTokenLifetime;
    }

    public int getJwtTokenLifetime() {
        return jwtTokenLifetime;
    }

    public void setJwtTokenLifetime(int jwtTokenLifetime) {
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
                ", activeClient=" + activeClient +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", refreshTokenValidity=" + refreshTokenValidity +
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
