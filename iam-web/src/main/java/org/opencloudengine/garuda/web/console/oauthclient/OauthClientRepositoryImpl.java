package org.opencloudengine.garuda.web.console.oauthclient;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.management.Management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OauthClientRepositoryImpl implements OauthClientRepository {

    private String NAMESPACE = "oauth_client";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Override
    public OauthClient insert(OauthClient oauthClient) {
        long time = new Date().getTime();
        oauthClient.setDocType(NAMESPACE);
        oauthClient.setRegDate(time);
        oauthClient.setUpdDate(time);

        Response response = serviceFactory.getDb().save(oauthClient);
        oauthClient.set_id(response.getId());
        oauthClient.set_rev(response.getRev());
        return oauthClient;
    }

    @Override
    public OauthClient selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<OauthClient> selectByManagementId(String managementId) {
        List<OauthClient> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementId");
            Key.ComplexKey complex = new Key().complex(managementId);
            List<ViewResponse.Row<Key.ComplexKey, OauthClient>> rows = builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthClient> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public OauthClient selectByManagementIdAndName(String managementId, String name) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndName");
            Key.ComplexKey complex = new Key().complex(managementId).add(name);
            return builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthClient selectByManagementIdAndId(String managementId, String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndId");
            Key.ComplexKey complex = new Key().complex(managementId).add(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthClient selectByClientKey(String clientKey) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientKey");
            Key.ComplexKey complex = new Key().complex(clientKey);
            return builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthClient selectByClientKeyAndSecret(String clientKey, String clientSecret) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientKeyAndSecret");
            Key.ComplexKey complex = new Key().complex(clientKey).add(clientSecret);
            return builder.newRequest(Key.Type.COMPLEX, OauthClient.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthClient updateById(OauthClient oauthClient) {
        OauthClient existClient = this.selectById(oauthClient.get_id());

        existClient = (OauthClient) JsonUtils.merge(existClient, oauthClient);
        long time = new Date().getTime();
        existClient.setUpdDate(time);

        Response update = serviceFactory.getDb().update(existClient);
        existClient.set_rev(update.getRev());
        return existClient;
    }

    @Override
    public void deleteById(String id) {
        OauthClient oauthClient = this.selectById(id);
        serviceFactory.getDb().remove(oauthClient);
    }

}
