package org.opencloudengine.garuda.web.console.oauthuser;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthUser implements Serializable {

    private Long id;
    private Long groupId;
    private String userName;
    private String userPassword;
    private Integer level;
    private String additionalInformation;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
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
        return "OauthUser{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", level=" + level +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
