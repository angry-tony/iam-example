package org.opencloudengine.garuda.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.opencloudengine.garuda.util.HttpUtils;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by uengine on 2016. 9. 8..
 */
public class IamClient {

    /**
     * SLF4J Application Logging
     */
    private Logger logger = LoggerFactory.getLogger(IamClient.class);

    private String schema = "http";

    private String host;

    private Integer port;

    private String oauthBasePath = "/oauth";

    private String restBasePath = "/rest/v1";

    private String clientId;

    private String clientSecret;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getOauthBasePath() {
        return oauthBasePath;
    }

    public void setOauthBasePath(String oauthBasePath) {
        this.oauthBasePath = oauthBasePath;
    }

    public String getRestBasePath() {
        return restBasePath;
    }

    public void setRestBasePath(String restBasePath) {
        this.restBasePath = restBasePath;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public IamClient(String host, int port, String clientId, String clientSecret) {
        this.host = host;
        this.port = port;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Map accessToken(AccessTokenRequest request) throws Exception {
        Map<String, Object> map = JsonUtils.convertClassToMap(request);
        Map<String, Object> params = new HashMap<>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                params.put(key, map.get(key));
            }
        }

        if (!params.containsKey("client_id")) {
            params.put("client_id", this.clientId);
        }
        if (!params.containsKey("client_secret")) {
            params.put("client_secret", this.clientSecret);
        }

        String queryString = HttpUtils.createPOSTQueryString(params);

        HttpUtils httpUtils = new HttpUtils();
        String url = createOauthBaseUrl() + "/access_token";

        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        HttpResponse response = httpUtils.makeRequest("POST", url, queryString, headers);

        HttpEntity entity = response.getEntity();
        String responseText = EntityUtils.toString(entity);

        Map unmarshal = JsonUtils.unmarshal(responseText);
        unmarshal.put("status", response.getStatusLine().getStatusCode());
        return unmarshal;
    }



    private String createOauthBaseUrl() {
        String portUrl = "";
        if (this.port != null) {
            portUrl = ":" + this.port;
        }
        return this.schema + "://" + this.host + portUrl + this.oauthBasePath;
    }

    private String createRestBaseUrl() {
        String portUrl = "";
        if (this.port != null) {
            portUrl = ":" + this.port;
        }
        return this.schema + "://" + this.host + portUrl + this.restBasePath;
    }

}
