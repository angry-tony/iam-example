package org.opencloudengine.garuda.mail;

import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.notification.NotificationType;

import javax.mail.internet.InternetAddress;

public interface MailService {

    void sendBySmtp(String subject, String text, String fromUser, String fromName, String toUser, String telephone, InternetAddress[] toCC);

    void registe(String userId, String token, String subject, String fromUser, String fromName, String toUser, InternetAddress[] toCC);

    void passwd(String userId, String token, String subject, String fromUser, String fromName, String toUser, InternetAddress[] toCC);

    void notificationMail(OauthClient oauthClient, OauthUser oauthUser, NotificationType notificationType, String redirect_url, String token) throws Exception;
}