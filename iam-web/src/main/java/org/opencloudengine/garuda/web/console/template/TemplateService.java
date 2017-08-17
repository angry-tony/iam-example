package org.opencloudengine.garuda.web.console.template;

import java.util.Map;

public interface TemplateService {

    Map selectByClientId(String clientId);

    Map selectByClientIdAndType(String clientId, String notification_type);

    Template selectByClientIdAndTypeAndLocale(String clientId, String notification_type, String locale);

    Template insertTemplate(String clientId, String notification_type, String locale, Template template);

    Template updateTemplate(Template template);

    Template setDefaultTemplate(String clientId, String notification_type, String locale);

    void deleteById(String id);

    Template getDefaultTemplate(NotificationType notificationType);
}
