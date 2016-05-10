package org.opencloudengine.garuda.web.oauth;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.velocity.app.VelocityEngine;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;
import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.HttpUtils;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.util.ResourceUtils;
import org.opencloudengine.garuda.util.StringUtils;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUser;
import org.opencloudengine.garuda.web.console.oauthuser.OauthUserService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.management.ManagementService;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import sun.org.mozilla.javascript.internal.NativeArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.*;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private OauthUserService oauthUserService;

    @Autowired
    private OauthClientService oauthClientService;

    @Override
    public boolean processTokenScript(Management management, OauthClient oauthClient, OauthUser oauthUser, String scope, String tokenType, String claimJson, String type) {
        Map map = this.runTokenScript(management, oauthClient, oauthUser, scope, tokenType, claimJson, type);

        if (!map.get("value").getClass().equals(Boolean.class)) {
            return false;
        } else {
            return (boolean) map.get("value");
        }
    }

    @Override
    public Map processTokenTest(Management management, String userId, String clientId,
                                String scopes, String tokenType, String claimJson, String script) {

        String type = "user";
        OauthUser oauthUser = null;
        if (StringUtils.isEmpty(userId)) {
            type = "client";
        }else{
            oauthUser = oauthUserService.selectById(userId);
        }
        OauthClient oauthClient = oauthClientService.selectById(clientId);
        management.setCustomTokenIssuance(script);
        Map map = this.runTokenScript(management, oauthClient, oauthUser, scopes, tokenType, claimJson, type);
        return map;
    }

    private Map runTokenScript(Management management, OauthClient oauthClient, OauthUser oauthUser, String scope, String tokenType, String claimJson, String type) {
        Map map = new HashMap();
        try {
            File file = ResourceUtils.getFile("classpath:rhino");
            String path = file.getAbsolutePath();

            Context cx = Context.enter();
            Global global = new Global(cx);
            cx.setOptimizationLevel(-1);
            cx.setLanguageVersion(Context.VERSION_1_5);

            Main.processFile(cx, global, path + "/env.rhino.1.2.js");

            Object wrappedManagement = Context.javaToJS(management, global);
            ScriptableObject.putProperty(global, "management", wrappedManagement);

            Object wrappedClient = Context.javaToJS(oauthClient, global);
            ScriptableObject.putProperty(global, "client", wrappedClient);

            Object wrappedUser = Context.javaToJS(oauthUser, global);
            ScriptableObject.putProperty(global, "user", wrappedUser);

            List<String> scopesNames = Arrays.asList(scope.split(","));
            Object wrappedScopeNames = Context.javaToJS(scopesNames, global);
            ScriptableObject.putProperty(global, "scope", wrappedScopeNames);

            ScriptableObject.putProperty(global, "token_type", tokenType);

            if (StringUtils.isEmpty(claimJson)) {
                claimJson = JsonUtils.marshal(new HashMap<>());
            }
            Object claim = Context.javaToJS(JsonUtils.marshal(claimJson), global);
            ScriptableObject.putProperty(global, "claim", claim);

            ScriptableObject.putProperty(global, "type", type);

            Map model = new HashMap();
            model.put("script", management.getCustomTokenIssuance());

            String engineJs = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "rhino/engine.js", "UTF-8", model);

            cx.evaluateString(global, engineJs, "script", 1, null);
            org.mozilla.javascript.Function run = (org.mozilla.javascript.Function) global.get("run", global);
            Object result = run.call(cx, global, global, new Object[]{});

            map = (Map) Context.jsToJava(result, Map.class);

        } catch (IOException ex) {
            map.put("log", "");
            map.put("value", false);

        } finally {
            Context.exit();
        }

        return map;
    }
}
