package org.opencloudengine.garuda.web.rest;


import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.Management;
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

    @RequestMapping(value = "/client", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> listAllClients(HttpServletRequest request) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            List<OauthClient> oauthClients = oauthClientService.selectByGroupId(management.getId());
            if (oauthClients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthClients, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthClient> getClient(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient oauthClient = oauthClientService.selectByGroupIdAndId(management.getId(), id);
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
            OauthClient existClient = oauthClientService.selectByGroupIdAndName(management.getId(), oauthClient.getName());
            if (existClient != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthClient createdClient = oauthClientService.createClient(management.getId(), oauthClient.getName(), oauthClient.getDescription(), oauthClient.getClientTrust()
                    , oauthClient.getClientType(), oauthClient.getActiveClient(), oauthClient.getAuthorizedGrantTypes(), oauthClient.getWebServerRedirectUri()
                    , oauthClient.getRefreshTokenValidity(), oauthClient.getAdditionalInformation(), oauthClient.getCodeLifetime(), oauthClient.getRefreshTokenLifetime()
                    , oauthClient.getAccessTokenLifetime(), oauthClient.getJwtTokenLifetime(), null);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/client/{id}").buildAndExpand(createdClient.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthClient> updateClient(HttpServletRequest request, @PathVariable("id") long id, @RequestBody OauthClient oauthClient) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthClient currentClient = oauthClientService.selectByGroupIdAndId(management.getId(), id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            oauthClient.setId(currentClient.getId());
            oauthClientService.updateById(oauthClient);

            currentClient = oauthClientService.selectByGroupIdAndId(management.getId(), id);
            return new ResponseEntity<>(currentClient, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthClient> deleteClient(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient currentClient = oauthClientService.selectByGroupIdAndId(management.getId(), id);

            if (currentClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthClientService.deleteById(currentClient.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/search/client", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthClient>> searchClient(HttpServletRequest request,
                                                        @RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String description,
                                                        @RequestParam(required = false) String clientTrust,
                                                        @RequestParam(required = false) String clientType,
                                                        @RequestParam(required = false) String activeClient,
                                                        @RequestParam(required = false) String authorizedGrantTypes,
                                                        @RequestParam(required = false) String webServerRedirectUri,
                                                        @RequestParam(required = false) String refreshTokenValidity,
                                                        @RequestParam(required = false) String additionalInformation,
                                                        @RequestParam(required = false) Integer codeLifetime,
                                                        @RequestParam(required = false) Integer refreshTokenLifetime,
                                                        @RequestParam(required = false) Integer accessTokenLifetime,
                                                        @RequestParam(required = false) Integer jwtTokenLifetime) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthClient oauthClient = new OauthClient();
            oauthClient.setGroupId(management.getId());
            oauthClient.setName(name);
            oauthClient.setDescription(description);
            oauthClient.setClientTrust(clientTrust);
            oauthClient.setClientType(clientType);
            oauthClient.setActiveClient(activeClient);
            oauthClient.setAuthorizedGrantTypes(authorizedGrantTypes);
            oauthClient.setWebServerRedirectUri(webServerRedirectUri);
            oauthClient.setRefreshTokenValidity(refreshTokenValidity);
            oauthClient.setAdditionalInformation(additionalInformation);
            oauthClient.setCodeLifetime(codeLifetime);
            oauthClient.setRefreshTokenLifetime(refreshTokenLifetime);
            oauthClient.setAccessTokenLifetime(accessTokenLifetime);
            oauthClient.setJwtTokenLifetime(jwtTokenLifetime);

            List<OauthClient> oauthClients = oauthClientService.selectByCondition(oauthClient);

            if (oauthClients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthClients, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
