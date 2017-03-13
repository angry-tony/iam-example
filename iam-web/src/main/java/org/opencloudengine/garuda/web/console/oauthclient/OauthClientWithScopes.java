package org.opencloudengine.garuda.web.console.oauthclient;

import org.opencloudengine.garuda.couchdb.CouchDAO;

/**
 * Created by uengine on 2015. 6. 3..
 */
public class OauthClientWithScopes extends OauthClient {

    private String scopes;

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }
}
