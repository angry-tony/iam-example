package org.opencloudengine.garuda.web.console.oauthscope;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.UnpaginatedRequestBuilder;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.mybatis.spring.SqlSessionTemplate;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.common.repository.PersistentRepositoryImpl;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
public class OauthScopeRepositoryImpl implements OauthScopeRepository {

    private String NAMESPACE = "oauth_scope";

    private String CLIENT_SCOPES_NAMESPACE = "oauth_client_scopes";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Override
    public OauthScope insert(OauthScope oauthScope) {
        long time = new Date().getTime();
        oauthScope.setDocType(NAMESPACE);
        oauthScope.setRegDate(time);
        oauthScope.setUpdDate(time);

        Response response = serviceFactory.getDb().save(oauthScope);
        oauthScope.set_id(response.getId());
        oauthScope.set_rev(response.getRev());
        return oauthScope;
    }

    @Override
    public OauthScope selectById(String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthScope.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    private List<OauthScope> selectById(List<String> idList) {
        List<OauthScope> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");

            ArrayList<Key.ComplexKey> complexKeys = new ArrayList<>();
            for (String id : idList) {
                complexKeys.add(new Key().complex(id));
            }

            Key.ComplexKey[] complex = new Key.ComplexKey[complexKeys.size()];
            complex = complexKeys.toArray(complex);

            List<ViewResponse.Row<Key.ComplexKey, OauthScope>> rows = builder.newRequest(Key.Type.COMPLEX, OauthScope.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthScope> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public List<OauthScope> selectByManagementId(String managementId) {
        List<OauthScope> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementId");
            Key.ComplexKey complex = new Key().complex(managementId);
            List<ViewResponse.Row<Key.ComplexKey, OauthScope>> rows = builder.newRequest(Key.Type.COMPLEX, OauthScope.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthScope> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public OauthScope selectByManagementIdAndName(String managementId, String name) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndName");
            Key.ComplexKey complex = new Key().complex(managementId).add(name);
            return builder.newRequest(Key.Type.COMPLEX, OauthScope.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthScope selectByManagementIdAndId(String managementId, String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndId");
            Key.ComplexKey complex = new Key().complex(managementId).add(id);
            return builder.newRequest(Key.Type.COMPLEX, OauthScope.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthScope updateById(OauthScope oauthScope) {
        OauthScope existScope = this.selectById(oauthScope.get_id());
        existScope = (OauthScope) JsonUtils.merge(existScope, oauthScope);
        long time = new Date().getTime();
        existScope.setUpdDate(time);

        Response update = serviceFactory.getDb().update(existScope);
        existScope.set_rev(update.getRev());

        return existScope;
    }

    @Override
    public void deleteById(String id) {
        OauthScope oauthScope = this.selectById(id);
        serviceFactory.getDb().remove(oauthScope);
    }

    @Override
    public OauthClientScopes insertClientScopes(OauthClientScopes oauthClientScopes) {
        long time = new Date().getTime();
        oauthClientScopes.setDocType(CLIENT_SCOPES_NAMESPACE);
        oauthClientScopes.setRegDate(time);
        oauthClientScopes.setUpdDate(time);

        Response response = serviceFactory.getDb().save(oauthClientScopes);
        oauthClientScopes.set_id(response.getId());
        oauthClientScopes.set_rev(response.getRev());
        return oauthClientScopes;
    }

    @Override
    public List<OauthScope> selectClientScopes(String clientId) {
        ArrayList<String> scopeIdList = new ArrayList<>();

        List<OauthClientScopes> clientScopes = this.selectClientScopesByClientId(clientId);
        for (OauthClientScopes clientScope : clientScopes) {
            String scopeId = clientScope.getScopeId();
            scopeIdList.add(scopeId);
        }

        return this.selectById(scopeIdList);
    }

    private List<OauthClientScopes> selectClientScopesByClientId(String clientId) {
        List<OauthClientScopes> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(CLIENT_SCOPES_NAMESPACE, "selectClientScopesByClientId");
            Key.ComplexKey complex = new Key().complex(clientId);
            List<ViewResponse.Row<Key.ComplexKey, OauthClientScopes>> rows = builder.newRequest(Key.Type.COMPLEX, OauthClientScopes.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthClientScopes> row : rows) {
                list.add(row.getValue());
            }
            return list;
        } catch (Exception ex) {
            return list;
        }
    }

    private OauthClientScopes selectClientScopesByClientIdAndScopeId(String clientId, String scopeId) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(CLIENT_SCOPES_NAMESPACE, "selectClientScopesByClientIdAndScopeId");
            Key.ComplexKey complex = new Key().complex(clientId).add(scopeId);
            return builder.newRequest(Key.Type.COMPLEX, OauthClientScopes.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthScope selectClientScopesByScopeId(String clientId, String scopeId) {

        OauthClientScopes oauthClientScopes = this.selectClientScopesByClientIdAndScopeId(clientId, scopeId);
        return this.selectById(oauthClientScopes.getScopeId());
    }

    @Override
    public void deleteClientScopes(String clientId){

        try{
            List<Map> documents = new ArrayList<>();
            List<OauthClientScopes> oauthClientScopes = this.selectClientScopesByClientId(clientId);

            for (OauthClientScopes clientScope : oauthClientScopes) {
                Map<String, Object> map = JsonUtils.convertClassToMap(clientScope);
                map.put("_deleted", true);
                documents.add(map);
            }
            serviceFactory.getDb().bulk(documents);
        }catch (IOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteClientScopesByScopeId(String clientId, String scopeId) {
        OauthClientScopes clientScopes = this.selectClientScopesByClientIdAndScopeId(clientId, scopeId);
        serviceFactory.getDb().remove(clientScopes);
    }
}
