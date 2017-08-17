package org.opencloudengine.garuda.web.console.oauthuser;

import org.opencloudengine.garuda.couchdb.CouchDAO;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthUserRegiste extends CouchDAO{

    private String requestType;
    private String token;
    private String redirect_url;
    private String lang;
    Long regDate;
    Long updDate;



}
