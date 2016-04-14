package org.opencloudengine.garuda.web.contactus;

import java.util.Date;

import static org.opencloudengine.garuda.common.logging.StringUtils.isEmpty;

public class ContactUs {

    long id;
    String name;
    String email;
    String telephone;
    String subject;
    String message;
    Date registration;

    public ContactUs() {
    }

    public boolean isValid() {
        if (isEmpty(name) || isEmpty(email) || isEmpty(subject) || isEmpty(message) || isEmpty(telephone)) {
            return false;
        }
        return true;
    }

    public void trim() {
        this.name = name.trim();
        this.email = email.trim();
        this.subject = subject.trim();
        this.message = message.trim();
        this.telephone = telephone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactUs{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", registration=").append(registration);
        sb.append('}');
        return sb.toString();
    }
}
