package org.opencloudengine.garuda.resourcepolicy.test;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.opencloudengine.garuda.resourcepolicy.MethodRPCondition;
import org.opencloudengine.garuda.resourcepolicy.ResourcePolicy;
import org.opencloudengine.garuda.resourcepolicy.ResourcePolicyCondition;
import org.opencloudengine.garuda.resourcepolicy.ResourcePolicyItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uengine on 2016. 5. 3..
 */
public class ResourcePolicyTest extends TestCase{

    ResourcePolicy resourcePolicy;

    @Override
    public void setUp(){
        resourcePolicy = new ResourcePolicy();
        resourcePolicy.setResourcePolicyItemList(new ArrayList<ResourcePolicyItem>());


        ResourcePolicyItem resourcePolicyItem = new ResourcePolicyItem();
        resourcePolicyItem.setScope("Read");
        resourcePolicyItem.setResourcePolicyCondition(new ArrayList<ResourcePolicyCondition>());

        MethodRPCondition resourcePolicyCondition = new MethodRPCondition();
        resourcePolicyCondition.setMethod("GET");

        resourcePolicyItem.getResourcePolicyCondition().add(resourcePolicyCondition);


        resourcePolicy.getResourcePolicyItemList().add(resourcePolicyItem);


    }


    public void testMain(){
        List<String> scopes = resourcePolicy.getScopes(new HttpRequest() {
            @Override
            public HttpMethod getMethod() {
                return HttpMethod.GET;
            }

            @Override
            public URI getURI() {
                return null;
            }

            @Override
            public HttpHeaders getHeaders() {
                return null;
            }
        });


        assertNotNull(scopes);
        assertEquals(scopes.size(), 1);
        assertEquals(scopes.get(0), "Read");
    }

}
