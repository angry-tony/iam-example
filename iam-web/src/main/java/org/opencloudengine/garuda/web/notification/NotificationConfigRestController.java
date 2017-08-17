package org.opencloudengine.garuda.web.notification;

import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.rest.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Properties;

/**
 * Created by uengine on 2017. 1. 11..
 */

@Controller
@RequestMapping("/rest/v1")
public class NotificationConfigRestController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private NotificationConfigRepository notificationConfigRepository;

    @RequestMapping(value = "/client/{clientId}/notification_config", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getNotificationConfiguration(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            @PathVariable("clientId") String clientId) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            NotificationConfig notificationConfig = notificationConfigRepository.selectConfigByClientId(clientId);

            //규칙이 생성되지 않은 조직이라면 디폴트 규칙을 생성해주도록 한다.
            if (notificationConfig == null) {
                notificationConfig = new NotificationConfig();
                notificationConfig.setClientId(clientId);
                notificationConfig.setConfiguration(notificationConfigRepository.getDefaultConfig());
                notificationConfig = notificationConfigRepository.insertConfig(notificationConfig);
            }
            Map map = JsonUtils.marshal(notificationConfig.getConfiguration());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/notification_config", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadNotificationConfiguration(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                @PathVariable("clientId") String clientId,
                                                                @RequestBody Map map, UriComponentsBuilder ucBuilder) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            NotificationConfig notificationConfig = notificationConfigRepository.selectConfigByClientId(clientId);
            notificationConfig.setConfiguration(JsonUtils.marshal(map));

            notificationConfigRepository.updateConfig(notificationConfig);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{clientId}/notification_config")
                    .buildAndExpand(clientId).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
