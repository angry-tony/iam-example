package org.opencloudengine.garuda.web.console.oauthregiste;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.web.security.AESPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class OauthRegisteRepositoryImpl implements OauthRegisteRepository {

    private String NAMESPACE = "oauth_registe";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Autowired
    AESPasswordEncoder passwordEncoder;

    @Override
    public OauthRegiste insert(OauthRegiste oauthRegiste) {
        long time = new Date().getTime();
        oauthRegiste.setDocType(NAMESPACE);
        oauthRegiste.setRegDate(time);
        oauthRegiste.setUpdDate(time);

        Response response = serviceFactory.getDb().save(oauthRegiste);
        oauthRegiste.set_id(response.getId());
        oauthRegiste.set_rev(response.getRev());

        return oauthRegiste;
    }

    @Override
    public OauthRegiste selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthRegiste.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthRegiste selectByClientIdAndTokenAndType(String clientId, String token, String notification_type) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByClientIdAndTokenAndType");
            Key.ComplexKey complex = new Key().complex(clientId).add(token).add(notification_type);
            return builder.newRequest(Key.Type.COMPLEX, OauthRegiste.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteById(String id) {
        OauthRegiste oauthRegiste = this.selectById(id);
        serviceFactory.getDb().remove(oauthRegiste);
    }
}
