package org.opencloudengine.garuda.web.management;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ManagementRepositoryImpl implements ManagementRepository {

    private String NAMESPACE = "management";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Override
    public Management insert(Management management) {
        long time = new Date().getTime();
        management.setDocType(NAMESPACE);
        management.setRegDate(time);
        management.setUpdDate(time);

        Response response = serviceFactory.getDb().save(management);
        management.set_id(response.getId());
        management.set_rev(response.getRev());
        return management;
    }

    @Override
    public Management selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, Management.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Management selectByKey(String managementKey) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByKey");
            Key.ComplexKey complex = new Key().complex(managementKey);
            return builder.newRequest(Key.Type.COMPLEX, Management.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Management selectByUserIdAndId(String userId, String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByUserIdAndId");
            Key.ComplexKey complex = new Key().complex(userId).add(id);
            return builder.newRequest(Key.Type.COMPLEX, Management.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Management> selectByUserId(String userId) {
        List<Management> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByUserId");
            Key.ComplexKey complex = new Key().complex(userId);
            List<ViewResponse.Row<Key.ComplexKey, Management>> rows = builder.newRequest(Key.Type.COMPLEX, Management.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, Management> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public Management selectByCredential(String managementKey, String managementSecret) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByCredential");
            Key.ComplexKey complex = new Key().complex(managementKey).add(managementSecret);
            return builder.newRequest(Key.Type.COMPLEX, Management.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Management updateById(String id, String managementName, String description, Integer sessionTokenLifetime, Integer scopeCheckLifetime) {

        Management management = this.selectById(id);
        management.setManagementName(managementName);
        management.setDescription(description);
        management.setSessionTokenLifetime(sessionTokenLifetime);
        management.setScopeCheckLifetime(scopeCheckLifetime);

        long time = new Date().getTime();
        management.setUpdDate(time);

        Response update = serviceFactory.getDb().update(management);
        management.set_rev(update.getRev());
        return management;
    }

    @Override
    public Management updateById(Management management) {
        Management existManagement = this.selectById(management.get_id());

        existManagement = (Management) JsonUtils.merge(existManagement, management);
        long time = new Date().getTime();
        existManagement.setUpdDate(time);

        Response update = serviceFactory.getDb().update(existManagement);
        existManagement.set_rev(update.getRev());
        return existManagement;
    }

    @Override
    public void deleteById(String id) {
        Management management = this.selectById(id);
        serviceFactory.getDb().remove(management);
    }
}
