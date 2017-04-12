package org.opencloudengine.garuda.web.console.oauthclient;


import org.apache.http.HttpResponse;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.util.JsonUtils;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.rest.RestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/rest/v1")
public class OauthClientRestController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private RestAuthService restAuthService;

    @Autowired
    private OauthScopeService oauthScopeService;

    @RequestMapping(value = "/client", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> listAllClients(HttpServletRequest request, HttpServletResponse response) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            List<OauthClient> oauthClients = oauthClientService.selectAllByManagementId(management.get_id());
            if (oauthClients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthClients, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/pagination", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> pagination(HttpServletRequest request, HttpServletResponse response,
                                                        @RequestParam(defaultValue = "0") int offset,
                                                        @RequestParam(defaultValue = "100") int limit) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            //총합
            Long max = oauthClientService.countAllByManagementId(management.get_id());

            //컨디션 합
            Long total = max;

            //컨디션 결과
            List<OauthClient> oauthClients = oauthClientService.selectByManagementId(management.get_id(), limit, new Long(offset));

            HttpHeaders headers = new HttpHeaders();
            headers.add("x-uengine-pagination-maxnbrecords", max + "");
            headers.add("x-uengine-pagination-currentoffset", offset + "");
            headers.add("x-uengine-pagination-totalnbrecords", total + "");

            return new ResponseEntity<>(oauthClients, headers, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/search/{searchKey}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> search(HttpServletRequest request, HttpServletResponse response,
                                                    @PathVariable("searchKey") String searchKey,
                                                    @RequestParam(defaultValue = "0") int offset,
                                                    @RequestParam(defaultValue = "100") int limit) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //총합
            Long max = oauthClientService.countAllByManagementId(management.get_id());

            //컨디션 합
            Long total = oauthClientService.countAllByManagementIdLikeClientName(management.get_id(), searchKey);

            //컨디션 결과
            List<OauthClient> oauthClients = oauthClientService.selectByManagementLikeClientName(management.get_id(), searchKey, limit, new Long(offset));

            HttpHeaders headers = new HttpHeaders();
            headers.add("x-uengine-pagination-maxnbrecords", max + "");
            headers.add("x-uengine-pagination-currentoffset", offset + "");
            headers.add("x-uengine-pagination-totalnbrecords", total + "");

            return new ResponseEntity<>(oauthClients, headers, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthClient> getClient(HttpServletRequest request, HttpServletResponse response, @PathVariable("_id") String _id) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthClient, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(HttpServletRequest request, HttpServletResponse response,
                                             @RequestBody OauthClientWithScopes oauthClient,
                                             UriComponentsBuilder ucBuilder) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient existClient = oauthClientService.selectByManagementIdAndName(management.get_id(), oauthClient.getName());
            if (existClient != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthClient createdClient = oauthClientService.createClient(management.get_id(), oauthClient.getName(), oauthClient.getDescription(), oauthClient.getClientTrust()
                    , oauthClient.getClientType(), oauthClient.getActiveClient(), oauthClient.getAuthorizedGrantTypes(), oauthClient.getWebServerRedirectUri()
                    , oauthClient.getRefreshTokenValidity(), oauthClient.getAutoDeletionToken(),
                    oauthClient.getRequiredContext(),
                    oauthClient.getCodeLifetime(), oauthClient.getRefreshTokenLifetime()
                    , oauthClient.getAccessTokenLifetime(), oauthClient.getJwtTokenLifetime(), oauthClient.getScopes());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{_id}").buildAndExpand(createdClient.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthClient> updateClient(HttpServletRequest request, HttpServletResponse response,
                                                    @PathVariable("_id") String _id, @RequestBody OauthClientWithScopes oauthClient) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            oauthClientService.updateById(_id, oauthClient.getName(), oauthClient.getDescription(),
                    oauthClient.getClientTrust(), oauthClient.getClientType(), oauthClient.getActiveClient(),
                    oauthClient.getAuthorizedGrantTypes(), oauthClient.getWebServerRedirectUri(),
                    oauthClient.getRefreshTokenValidity(), oauthClient.getAutoDeletionToken(),
                    oauthClient.getRequiredContext(),
                    oauthClient.getCodeLifetime(), oauthClient.getRefreshTokenLifetime(),
                    oauthClient.getAccessTokenLifetime(), oauthClient.getJwtTokenLifetime(), oauthClient.getScopes());

            currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
            return new ResponseEntity<>(currentClient, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthClient> deleteClient(HttpServletRequest request, HttpServletResponse response,
                                                    @PathVariable("_id") String _id) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthClientService.deleteById(currentClient.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthScope>> listAllClientScopes(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("clientId") String clientId) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<OauthScope> oauthScopes = oauthScopeService.selectClientScopes(clientId);
            if (oauthScopes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthScopes, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthScope> getClientScope(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            OauthScope oauthScope = oauthScopeService.selectClientScopesByScopeId(clientId, scopeId);
            if (oauthScope == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthScope, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createClientScope(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId, UriComponentsBuilder ucBuilder) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            OauthScope existScope = oauthScopeService.selectClientScopesByScopeId(clientId, scopeId);
            if (existScope != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthClientScopes clientScopes = new OauthClientScopes();
            clientScopes.setClientId(clientId);
            clientScopes.setScopeId(scopeId);
            oauthScopeService.insertClientScopes(clientScopes);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{clientId}/scope/${scopeId}").
                    buildAndExpand(clientScopes.getClientId(), clientScopes.getScopeId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }


    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<OauthScope> deleteClientScope(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            OauthScope currentScope = oauthScopeService.selectClientScopesByScopeId(clientId, scopeId);

            if (currentScope == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthScopeService.deleteClientScopesByScopeId(clientId, scopeId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
