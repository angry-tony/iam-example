/**
 * Copyright (C) 2011 uEngine Project (http://www.uengine.io).
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opencloudengine.garuda.web.console.template;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.ResourceUtils;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Seungpil PARK
 */
@Repository
public class TemplateRepositoryImpl implements TemplateRepository {

    private String NAMESPACE = "oauth_client_template";

    @Autowired
    private CouchServiceFactory serviceFactory;

    @Override
    public List<Template> selectByClientId(String clientId) {
        List<Template> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientId");
            Key.ComplexKey complex = new Key().complex(clientId);
            List<ViewResponse.Row<Key.ComplexKey, Template>> rows = builder.newRequest(Key.Type.COMPLEX, Template.class).
                    keys(complex).
                    build().getResponse().getRows();
            for (ViewResponse.Row<Key.ComplexKey, Template> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public List<Template> selectByClientIdAndType(String clientId, String notification_type) {
        List<Template> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientIdAndType");
            Key.ComplexKey complex = new Key().complex(clientId).add(notification_type);
            List<ViewResponse.Row<Key.ComplexKey, Template>> rows = builder.newRequest(Key.Type.COMPLEX, Template.class).
                    keys(complex).
                    build().getResponse().getRows();
            for (ViewResponse.Row<Key.ComplexKey, Template> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public Template selectByClientIdAndTypeAndLocale(String clientId, String notification_type, String locale) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientIdAndTypeAndLocale");
            Key.ComplexKey complex = new Key().complex(clientId).add(notification_type).add(locale);
            return builder.newRequest(Key.Type.COMPLEX, Template.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Template selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, Template.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Template insert(Template template) {
        long time = new Date().getTime();
        template.setDocType(NAMESPACE);
        template.setRegDate(time);
        template.setUpdDate(time);

        Response response = serviceFactory.getDb().save(template);
        template.set_id(response.getId());
        template.set_rev(response.getRev());
        return template;
    }

    @Override
    public Template updateById(Template template) {
        Template existTemplate = this.selectById(template.get_id());

        existTemplate = (Template) JsonUtils.merge(existTemplate, template);
        long time = new Date().getTime();
        existTemplate.setUpdDate(time);

        Response update = serviceFactory.getDb().update(existTemplate);
        existTemplate.set_rev(update.getRev());

        return existTemplate;
    }

    @Override
    public Template setDefaultTemplate(String clientId, String notification_type, String locale) {
        List<Template> templates = this.selectByClientIdAndType(clientId, notification_type);
        if (templates == null) {
            return null;
        }
        for (Template template : templates) {
            if (template.getLocale().equals(locale)) {
                template.setIs_default("Y");
                this.updateById(template);
            } else {
                template.setIs_default("N");
                this.updateById(template);
            }
        }
        return this.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);
    }

    @Override
    public void deleteById(String id) {
        Template template = this.selectById(id);
        serviceFactory.getDb().remove(template);
    }

    @Override
    public Template getDefaultTemplate(NotificationType notificationType) {
        try {
            String defaultLocale = "en_US";

            URL subjectUrl = getClass().getResource("/template/" + notificationType.toString() + "-default-subject.txt");
            File subjectFile = ResourceUtils.getFile(subjectUrl);
            String subject = new String(Files.readAllBytes(subjectFile.toPath()));

            URL bodyUrl = getClass().getResource("/template/" + notificationType.toString() + "-default-body.html");
            File bodyFile = ResourceUtils.getFile(bodyUrl);
            String body = new String(Files.readAllBytes(bodyFile.toPath()));

            Template template = new Template();
            template.setSubject(subject);
            template.setBody(body);
            template.setLocale(defaultLocale);
            template.setNotification_type(notificationType.toString());
            return template;
        } catch (IOException e) {
            throw new ServiceException("Failed to read default template");
        }
    }
}