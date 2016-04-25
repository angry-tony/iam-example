package org.opencloudengine.garuda.mail;

import javax.mail.internet.InternetAddress;

public interface MailService {

    void sendBySmtp(String subject, String text, String fromUser, String fromName, String toUser, String telephone, InternetAddress[] toCC);

    void registe(Long userId, String token, String subject, String fromUser, String fromName, String toUser, InternetAddress[] toCC);

    void passwd(Long userId, String token, String subject, String fromUser, String fromName, String toUser, InternetAddress[] toCC);
}