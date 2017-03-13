package org.opencloudengine.garuda.web.console.oauthclient;


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
    public ResponseEntity<List<OauthClient>> listAllClients(HttpServletRequest request) {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/pagination", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> pagination(HttpServletRequest request,
                                                      @RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "100") int limit) {

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
    }

    @RequestMapping(value = "/client/search/{searchKey}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> search(HttpServletRequest request,
                                                      @PathVariable("searchKey") String searchKey,
                                                      @RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "100") int limit) {

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
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthClient> getClient(HttpServletRequest request, @PathVariable("_id") String _id) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthClient, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<Void> createClient(HttpServletRequest request, @RequestBody OauthClient oauthClient, UriComponentsBuilder ucBuilder) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient existClient = oauthClientService.selectByManagementIdAndName(management.get_id(), oauthClient.getName());
            if (existClient != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthClient createdClient = oauthClientService.createClient(management.get_id(), oauthClient.getName(), oauthClient.getDescription(), oauthClient.getClientTrust()
                    , oauthClient.getClientType(), oauthClient.getActiveClient(), oauthClient.getAuthorizedGrantTypes(), oauthClient.getWebServerRedirectUri()
                    , oauthClient.getRefreshTokenValidity(), oauthClient.getCodeLifetime(), oauthClient.getRefreshTokenLifetime()
                    , oauthClient.getAccessTokenLifetime(), oauthClient.getJwtTokenLifetime(), null);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{_id}").buildAndExpand(createdClient.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthClient> updateClient(HttpServletRequest request, @PathVariable("_id") String _id, @RequestBody OauthClient oauthClient) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            oauthClient.set_id(currentClient.get_id());
            oauthClientService.updateById(oauthClient);

            currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);
            return new ResponseEntity<>(currentClient, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthClient> deleteClient(HttpServletRequest request, @PathVariable("_id") String _id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient currentClient = oauthClientService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthClientService.deleteById(currentClient.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthScope>> listAllClientScopes(
            HttpServletRequest request, @PathVariable("clientId") String clientId) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthScope> getClientScope(
            HttpServletRequest request, @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createClientScope(
            HttpServletRequest request, @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId, UriComponentsBuilder ucBuilder) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/client/{clientId}/scope/{scopeId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<OauthScope> deleteClientScope(
            HttpServletRequest request, @PathVariable("clientId") String clientId, @PathVariable("scopeId") String scopeId) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
