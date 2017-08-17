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
package org.opencloudengine.garuda.web.notification;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author Seungpil PARK
 */
@Repository
public class NotificationConfigRepositoryImpl implements NotificationConfigRepository {

    private String NAMESPACE = "oauth_client_notification";

    @Autowired
    private CouchServiceFactory serviceFactory;


    @Override
    public NotificationConfig selectConfigByClientId(String clientId) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectConfigByClientId");
            Key.ComplexKey complex = new Key().complex(clientId);
            return builder.newRequest(Key.Type.COMPLEX, NotificationConfig.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public NotificationConfig insertConfig(NotificationConfig notificationConfig) {
        long time = new Date().getTime();
        notificationConfig.setDocType(NAMESPACE);
        notificationConfig.setRegDate(time);
        notificationConfig.setUpdDate(time);

        Response response = serviceFactory.getDb().save(notificationConfig);
        notificationConfig.set_id(response.getId());
        notificationConfig.set_rev(response.getRev());
        return notificationConfig;
    }

    @Override
    public NotificationConfig updateConfig(NotificationConfig notificationConfig) {
        NotificationConfig existConfig = this.selectConfigByClientId(notificationConfig.getClientId());

        existConfig = (NotificationConfig) JsonUtils.merge(existConfig, notificationConfig);
        long time = new Date().getTime();
        existConfig.setUpdDate(time);

        Response update = serviceFactory.getDb().update(existConfig);
        existConfig.set_rev(update.getRev());

        return existConfig;
    }

    @Override
    public void deleteConfigByClientId(String clientId) {
        NotificationConfig existConfig = this.selectConfigByClientId(clientId);
        serviceFactory.getDb().remove(existConfig);
    }

    @Override
    public String getDefaultConfig() {
        try {
            URL url = getClass().getResource("/notification/default-notification-config.json");
            File ruleFile = ResourceUtils.getFile(url);
            String json = new String(Files.readAllBytes(ruleFile.toPath()));
            return json;
        } catch (IOException e) {
            throw new ServiceException("Failed to read default notification configuration");
        }
    }
}