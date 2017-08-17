package org.opencloudengine.garuda.mail;

import com.google.common.base.Strings;
import com.samskivert.mustache.Mustache;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.velocity.app.Velocity;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.template.Template;
import org.opencloudengine.garuda.web.console.template.TemplateRepository;
import org.opencloudengine.garuda.web.notification.NotificationConfig;
import org.opencloudengine.garuda.web.notification.NotificationConfigRepository;
import org.opencloudengine.garuda.web.notification.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import reactor.core.Reactor;
import reactor.event.Event;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;


@Service
public class MailServiceImpl implements MailService {

    /**
     * SLF4J Logging
     */
    private Logger logger = LoggerFactory.getLogger(MailService.class);


    @Autowired
    private Reactor reactor;


    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    NotificationConfigRepository notificationConfigRepository;

    @Override
    public void sendBySmtp(String subject, String text, String fromUser, String fromName, final String toUser, String telephone, InternetAddress[] toCC) {
        Map map = new HashMap();
        map.put("command", "sendBySmtp");
        map.put("subject", subject);
        map.put("text", text);
        map.put("fromUser", fromUser);
        map.put("fromName", fromName);
        map.put("toUser", toUser);
        map.put("telephone", telephone);
        map.put("toCC", toCC);

        reactor.notify("mail", Event.wrap(map));
    }

    @Override
    public void registe(String userId, String token, String subject, String fromUser, String fromName, final String toUser, InternetAddress[] toCC) {
        Map map = new HashMap();
        map.put("command", "registe");
        map.put("userId", userId);
        map.put("token", token);
        map.put("subject", subject);
        map.put("fromUser", fromUser);
        map.put("fromName", fromName);
        map.put("toUser", toUser);
        map.put("toCC", toCC);

        reactor.notify("mail", Event.wrap(map));

    }

    @Override
    public void passwd(String userId, String token, String subject, String fromUser, String fromName, final String toUser, InternetAddress[] toCC) {
        Map map = new HashMap();
        map.put("command", "passwd");
        map.put("userId", userId);
        map.put("token", token);
        map.put("subject", subject);
        map.put("fromUser", fromUser);
        map.put("fromName", fromName);
        map.put("toUser", toUser);
        map.put("toCC", toCC);

        reactor.notify("mail", Event.wrap(map));
    }

    @Override
    public void notificationMail(OauthClient oauthClient, OauthUser oauthUser, NotificationType notificationType, String redirect_url, String token) throws Exception {

        NotificationConfig notificationConfig = notificationConfigRepository.selectConfigByClientId(oauthClient.get_id());
        Map config = JsonUtils.unmarshal(notificationConfig.getConfiguration());

        //이메일이 없다면 에러.
        if(oauthUser.get("email") == null){
            throw new ServiceException("OauthUser email field is required. " + oauthUser.getUserName());
        }

        //클라이언트에 노티타입이 허용인지 확인한다.
        if (!config.containsKey(notificationType.toString())) {
            return;
        }
        if (!(boolean) config.get(notificationType.toString())) {
            return;
        }


        //사용자의 로케일이 있다면 해당하는 템플릿을 불러온다.
        Template currentTemplate = null;
        if (oauthUser.get("locale") != null) {
            currentTemplate = templateRepository.selectByClientIdAndTypeAndLocale(oauthClient.get_id(), notificationType.toString(), oauthUser.get("locale").toString());
        }

        //로케일에 해당하는 템플릿이 없거나 사용자의 로케일이 없다면 디폴트 템플릿을 불러온다.
        if (currentTemplate == null || oauthUser.get("locale") == null) {
            List<Template> templates = templateRepository.selectByClientIdAndType(oauthClient.get_id(), notificationType.toString());
            for (Template template : templates) {
                if ("Y".equals(template.getIs_default())) {
                    currentTemplate = template;
                }
            }
        }

        //템플릿을 찾지 못했다면 에러
        if (currentTemplate == null) {
            throw new ServiceException("Failed to find notification template for " + notificationType.toString());
        }

        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("client", oauthClient);
        model.put("user", oauthUser);

        //SIGN_UP 이거나 FORGOT_PASSWORD 일 경우 link 를 생성한다.
        if(notificationType.equals(NotificationType.SIGN_UP)
                || notificationType.equals(NotificationType.FORGOT_PASSWORD)){

            String query = new URL(redirect_url).getQuery();
            if(StringUtils.isEmpty(query)){
                model.put("link", MessageFormatter.arrayFormat(redirect_url + "?token={}", new Object[]{token}).getMessage());
            }else{
                model.put("link", MessageFormatter.arrayFormat(redirect_url + "&token={}", new Object[]{token}).getMessage());
            }
        }

        String body = executeTemplateText(currentTemplate.getBody(), model);
        String subject = executeTemplateText(currentTemplate.getSubject(), model);

        Map map = new HashMap();
        map.put("command", "notificationMail");
        map.put("subject", subject);
        map.put("body", body);
        map.put("fromAddress", config.get("FROM").toString());
        map.put("fromName", config.get("FROM_NAME").toString());
        map.put("toUser", oauthUser.get("email").toString());

        reactor.notify("mail", Event.wrap(map));
    }

    public String executeTemplateText(final String templateText, final Map<String, Object> data) {
        final com.samskivert.mustache.Template template = Mustache.compiler().nullValue("").compile(templateText);
        return template.execute(data);
    }
}