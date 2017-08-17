package org.opencloudengine.garuda.web.console.template;

import org.opencloudengine.garuda.web.notification.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private TemplateRepository templateRepository;


    private Logger logger = LoggerFactory.getLogger(TemplateService.class);


    /**
     * 주어진 클라이언트의 모든 템플릿을 불러온다.
     *
     * @param clientId
     * @return
     */
    @Override
    public Map selectByClientId(String clientId) {
        List<Template> templates = templateRepository.selectByClientId(clientId);
        if (templates == null) {
            templates = new ArrayList<Template>();
        }

        //타입별로 정렬
        Map<String, ArrayList<Template>> map = new HashMap();
        for (Template template : templates) {
            String notification_type = template.getNotification_type();
            if (!map.containsKey(notification_type)) {
                map.put(notification_type, new ArrayList<Template>());
            }
            map.get(notification_type).add(template);
        }

        //타입별로 누락된 템플릿 인서트
        for (int i = 0; i < NotificationType.values().length; i++) {
            String requiredType = NotificationType.values()[i].toString();
            if (isEmptyTemplate(requiredType, map)) {
                map.get(requiredType).add(this.insertDefaultTemplate(clientId, requiredType));
            }
        }
        return map;
    }

    /**
     * 주어진 클라이언트의 알림타입에 디폴트 템플릿을 생성한다.
     *
     * @param clientId
     * @param notification_type
     * @return
     */
    private Template insertDefaultTemplate(String clientId, String notification_type) {

        Template template = this.getDefaultTemplate(NotificationType.valueOf(notification_type));
        template.setIs_default("Y");
        return this.insertTemplate(clientId, notification_type, template.getLocale(), template);
    }

    /**
     * 주어진 클라이언트의 알림 타입의 모든 템플릿을 가져온다.
     *
     * @param clientId
     * @param notification_type
     * @return
     */
    @Override
    public Map selectByClientIdAndType(String clientId, String notification_type) {
        List<Template> templates = templateRepository.selectByClientIdAndType(clientId, notification_type);
        if (templates == null || templates.isEmpty()) {
            Template template = this.insertDefaultTemplate(clientId, notification_type);
            templates = new ArrayList<>();
            templates.add(template);
        }
        Map map = new HashMap();
        map.put(notification_type, templates);
        return map;
    }

    @Override
    public Template selectByClientIdAndTypeAndLocale(String clientId, String notification_type, String locale) {
        return templateRepository.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);
    }

    @Override
    public Template insertTemplate(String clientId, String notification_type, String locale, Template template) {
        Template existTemplate = this.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);

        //기존에 템플릿이 있다면 업데이트 한다.
        if (existTemplate != null) {
            existTemplate.setSubject(template.getSubject());
            existTemplate.setBody(template.getBody());
            existTemplate = this.updateTemplate(existTemplate);
        }
        //기존에 템플릿이 없다면 신규 저장
        else {
            template.setClientId(clientId);
            template.setNotification_type(notification_type);
            template.setLocale(locale);
            existTemplate = templateRepository.insert(template);
        }
        if ("Y".equals(template.getIs_default())) {
            this.setDefaultTemplate(clientId, notification_type, locale);
        }
        return existTemplate;
    }

    @Override
    public Template updateTemplate(Template template) {
        return templateRepository.updateById(template);
    }

    @Override
    public Template setDefaultTemplate(String clientId, String notification_type, String locale) {
        return templateRepository.setDefaultTemplate(clientId, notification_type, locale);
    }

    @Override
    public void deleteById(String id) {
        templateRepository.deleteById(id);
    }

    @Override
    public Template getDefaultTemplate(NotificationType notificationType) {
        return templateRepository.getDefaultTemplate(notificationType);
    }

    /**
     * 템플릿 타입별 해쉬맵에서 주어진 타입이 비었는지 확인하고 비었을 경우 템플릿 어레이를 추가한다.
     *
     * @param requiredType
     * @param map
     * @return
     */
    private boolean isEmptyTemplate(String requiredType, Map<String, ArrayList<Template>> map) {
        if (map.get(requiredType) == null) {
            map.put(requiredType, new ArrayList<Template>());
            return true;
        }
        if (map.get(requiredType).size() < 1) {
            map.put(requiredType, new ArrayList<Template>());
            return true;
        }
        return false;
    }
}
