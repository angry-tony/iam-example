package org.opencloudengine.garuda.web.console.oauthuser;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OauthUserRepositoryImpl implements OauthUserRepository {

    private String NAMESPACE = "oauth_user";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Override
    public OauthUser insert(OauthUser oauthUser) {
        long time = new Date().getTime();
        oauthUser.setDocType(NAMESPACE);
        oauthUser.setRegDate(time);
        oauthUser.setUpdDate(time);

        Response response = serviceFactory.getDb().save(oauthUser);
        oauthUser.set_id(response.getId());
        oauthUser.set_rev(response.getRev());
        return oauthUser;
    }

    @Override
    public OauthUser selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<OauthUser> selectByManagementId(String managementId) {
        List<OauthUser> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementId");
            Key.ComplexKey complex = new Key().complex(managementId);
            List<ViewResponse.Row<Key.ComplexKey, OauthUser>> rows = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthUser> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public OauthUser selectByManagementIdAndUserName(String managementId, String userName) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndUserName");
            Key.ComplexKey complex = new Key().complex(managementId).add(userName);
            return builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser selectByManagementIdAndCredential(String managementId, String userName, String userPassword) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndCredential");
            Key.ComplexKey complex = new Key().complex(managementId).add(userName).add(userPassword);
            return builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser selectByManagementIdAndId(String managementId, String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndId");
            Key.ComplexKey complex = new Key().complex(managementId).add(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser updateById(OauthUser oauthUser) {
        OauthUser existUser = this.selectById(oauthUser.get_id());

        //변경될 수 없는 값들을 보호한다.
        oauthUser.set_id(existUser.get_id());
        oauthUser.set_rev(existUser.get_rev());
        oauthUser.setDocType(existUser.getDocType());
        oauthUser.setManagementId(existUser.getManagementId());
        oauthUser.setRegDate(existUser.getRegDate());

        long time = new Date().getTime();
        existUser.setUpdDate(time);

        Response update = serviceFactory.getDb().update(oauthUser);
        oauthUser.set_rev(update.getRev());
        return oauthUser;
    }

    @Override
    public void deleteById(String id) {
        OauthUser oauthUser = this.selectById(id);
        serviceFactory.getDb().remove(oauthUser);
    }
}
