package org.opencloudengine.garuda.web.console.oauthuser;

import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;
import com.cloudant.client.api.views.ViewRequestBuilder;
import com.cloudant.client.api.views.ViewResponse;
import org.opencloudengine.garuda.couchdb.CouchServiceFactory;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.security.AESPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OauthUserRepositoryImpl implements OauthUserRepository {

    private String NAMESPACE = "oauth_user";

    @Autowired
    CouchServiceFactory serviceFactory;

    @Autowired
    AESPasswordEncoder passwordEncoder;


    @Override
    public OauthUser insert(OauthUser oauthUser) {
        oauthUser.setUserPassword(passwordEncoder.encode(oauthUser.getUserPassword()));

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
    public OauthUser selectById(String userName) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectById");
            Key.ComplexKey complex = new Key().complex(userName);
            OauthUser value = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
            value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
            return value;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser selectByName(String name) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByName");
            Key.ComplexKey complex = new Key().complex(name);
            OauthUser value = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
            value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
            return value;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<OauthUser> selectAllByManagementId(String managementId) {
        List<OauthUser> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementId");
            Key.ComplexKey complex = new Key().complex(managementId);
            List<ViewResponse.Row<Key.ComplexKey, OauthUser>> rows = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthUser> row : rows) {
                OauthUser value = row.getValue();
                value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
                list.add(value);
            }
            return list;

        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public List<OauthUser> selectByManagementId(String managementId, int limit, Long skip) {
        List<OauthUser> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementId");
            Key.ComplexKey complex = new Key().complex(managementId);
            List<ViewResponse.Row<Key.ComplexKey, OauthUser>> rows = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).limit(limit).skip(skip).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthUser> row : rows) {
                OauthUser value = row.getValue();
                value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
                list.add(value);
            }
            return list;

        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public List<OauthUser> selectByManagementLikeUserName(String managementId, String userName, int limit, Long skip) {
        List<OauthUser> list = new ArrayList<>();
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdLikeUserName");
            Key.ComplexKey startKey = new Key().complex(userName).add(managementId);
            Key.ComplexKey endKey = new Key().complex(userName + "Z").add(managementId);

            List<ViewResponse.Row<Key.ComplexKey, OauthUser>> rows = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    startKey(startKey).endKey(endKey).limit(limit).skip(skip).
                    build().getResponse().getRows();

            for (ViewResponse.Row<Key.ComplexKey, OauthUser> row : rows) {
                OauthUser value = row.getValue();
                if (managementId.equals(value.getManagementId())) {
                    value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
                    list.add(value);
                }
            }
            return list;

        } catch (Exception ex) {
            return list;
        }
    }

    @Override
    public Long countAllByManagementId(String managementId) {
        Long count = null;
        Key.ComplexKey complex;

        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "countByManagementId");
            complex = new Key().complex(managementId);

            count = builder.newRequest(Key.Type.COMPLEX, Long.class).
                    keys(complex).reduce(true).
                    build().getResponse().getRows().get(0).getValue();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public Long countAllByManagementIdLikeUserName(String managementId, String userName) {
        Long count = null;
        Key.ComplexKey startKey;
        Key.ComplexKey endKey;

        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "countByManagementIdLikeUserName");
            startKey = new Key().complex(userName).add(managementId);
            endKey = new Key().complex(userName + "Z").add(managementId);

            count = builder.newRequest(Key.Type.COMPLEX, Long.class).
                    startKey(startKey).endKey(endKey).reduce(true).
                    build().getResponse().getRows().get(0).getValue();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public OauthUser selectByManagementIdAndUserName(String managementId, String userName) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndUserName");
            Key.ComplexKey complex = new Key().complex(managementId).add(userName);
            OauthUser value = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
            value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
            return value;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser selectByManagementIdAndCredential(String managementId, String userName, String userPassword) {
        try {
            userPassword = passwordEncoder.encode(userPassword);
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndCredential");
            Key.ComplexKey complex = new Key().complex(managementId).add(userName).add(userPassword);
            OauthUser value = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
            value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
            return value;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser selectByManagementIdAndId(String managementId, String id) {
        try {
            ViewRequestBuilder builder = serviceFactory.getDb().getViewRequestBuilder(NAMESPACE, "selectByManagementIdAndId");
            Key.ComplexKey complex = new Key().complex(managementId).add(id);
            OauthUser value = builder.newRequest(Key.Type.COMPLEX, OauthUser.class).
                    keys(complex).
                    build().getResponse().getRows().get(0).getValue();
            value.setUserPassword(passwordEncoder.decode(value.getUserPassword()));
            return value;

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OauthUser updateById(OauthUser oauthUser) {
//        if (!StringUtils.isEmpty(oauthUser.getUserPassword())) {
//            oauthUser.setUserPassword(passwordEncoder.encode(oauthUser.getUserPassword()));
//        }

        OauthUser existUser = this.selectById(oauthUser.get_id());

        //변경될 수 없는 값들을 보호한다.
        oauthUser.set_id(existUser.get_id());
        oauthUser.set_rev(existUser.get_rev());
        oauthUser.setDocType(existUser.getDocType());
        oauthUser.setManagementId(existUser.getManagementId());
        oauthUser.setRegDate(existUser.getRegDate());

        //existUser 에서 지정된 메소드 이외의 값을 제거한다.
        existUser = JsonUtils.merge(new OauthUser(), existUser);

        //existUser 와 업데이트객체를 머지한다.
        existUser.putAll(oauthUser);

        long time = new Date().getTime();
        existUser.setUpdDate(time);
        existUser.setUserPassword(passwordEncoder.encode(existUser.getUserPassword()));

        Response update = serviceFactory.getDb().update(existUser);
        existUser.set_rev(update.getRev());

        return existUser;
    }

    @Override
    public void deleteById(String id) {
        OauthUser oauthUser = this.selectById(id);
        serviceFactory.getDb().remove(oauthUser);
    }

    @Override
    public OauthUser insertAvatar(InputStream in, String contentType, OauthUser oauthUser) {
        try {
            this.deleteAvatar(oauthUser);
        } catch (Exception ex) {
        }
        oauthUser = this.selectById(oauthUser.get_id());
        serviceFactory.getDb().saveAttachment(in, "avatar", contentType, oauthUser.get_id(), oauthUser.get_rev());
        return this.selectById(oauthUser.get_id());
    }


    @Override
    public void deleteAvatar(OauthUser oauthUser) {
        serviceFactory.getDb().removeAttachment(oauthUser, "avatar");
    }
}
