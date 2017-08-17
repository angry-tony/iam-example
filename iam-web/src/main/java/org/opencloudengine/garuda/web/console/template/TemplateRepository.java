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


import org.opencloudengine.garuda.web.notification.NotificationType;

import java.util.List;

/**
 * @author Seungpil PARK
 */
public interface TemplateRepository {

    String NAMESPACE = TemplateRepository.class.getName();

    List<Template> selectByClientId(String clientId);

    List<Template> selectByClientIdAndType(String clientId, String notification_type);

    Template selectByClientIdAndTypeAndLocale(String clientId, String notification_type, String locale);

    Template selectById(String id);

    Template insert(Template template);

    Template updateById(Template template);

    Template setDefaultTemplate(String clientId, String notification_type, String locale);

    void deleteById(String id);

    Template getDefaultTemplate(NotificationType notificationType);
}
