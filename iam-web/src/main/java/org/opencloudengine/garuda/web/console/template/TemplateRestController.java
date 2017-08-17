package org.opencloudengine.garuda.web.console.template;

import org.opencloudengine.garuda.util.ExceptionUtils;
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
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by uengine on 2017. 1. 11..
 */

@Controller
@RequestMapping("/rest/v1")
public class TemplateRestController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private RestAuthService restAuthService;

    @RequestMapping(value = "/client/{clientId}/template", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getAllTemplate(HttpServletRequest request,
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

            Map map = templateService.selectByClientId(clientId);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getTemplateByType(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @PathVariable("clientId") String clientId,
                                                 @PathVariable("notification_type") String notification_type) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Map map = templateService.selectByClientIdAndType(clientId, notification_type);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTemplateByType(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @PathVariable("clientId") String clientId,
                                                     @PathVariable("notification_type") String notification_type) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Template> templates = templateRepository.selectByClientIdAndType(clientId, notification_type);
            if (templates != null) {
                for (Template template : templates) {
                    templateService.deleteById(template.get_id());
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}/{locale}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Template> getTemplateByTypeAndLocale(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               @PathVariable("clientId") String clientId,
                                                               @PathVariable("notification_type") String notification_type,
                                                               @PathVariable("locale") String locale) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Template template = templateService.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);
            if (template == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(template, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}/{locale}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTemplateByTypeAndLocale(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              @PathVariable("clientId") String clientId,
                                                              @PathVariable("notification_type") String notification_type,
                                                              @PathVariable("locale") String locale) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {

            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Template template = templateService.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);
            if (template == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            templateService.deleteById(template.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}/{locale}", method = RequestMethod.POST)
    public ResponseEntity<Void> createTemplate(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @PathVariable("clientId") String clientId,
                                               @PathVariable("notification_type") String notification_type,
                                               @PathVariable("locale") String locale,
                                               @RequestBody Template template, UriComponentsBuilder ucBuilder) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            templateService.insertTemplate(clientId, notification_type, locale, template);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{clientId}/template/{notification_type}/{locale}")
                    .buildAndExpand(clientId, notification_type, locale).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/template/{notification_type}/{locale}", method = RequestMethod.PUT)
    public ResponseEntity<Template> setDefaultTemplate(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       @PathVariable("clientId") String clientId,
                                                       @PathVariable("notification_type") String notification_type,
                                                       @PathVariable("locale") String locale) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Template template = templateService.selectByClientIdAndTypeAndLocale(clientId, notification_type, locale);
            if (template == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            template = templateService.setDefaultTemplate(clientId, notification_type, locale);

            return new ResponseEntity<>(template, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
