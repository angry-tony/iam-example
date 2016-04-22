package org.opencloudengine.garuda.web.rest;


import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientService;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScope;
import org.opencloudengine.garuda.web.console.oauthscope.OauthScopeService;
import org.opencloudengine.garuda.web.management.Management;
import org.opencloudengine.garuda.web.oauth.OauthAccessToken;
import org.opencloudengine.garuda.web.oauth.OauthTokenService;
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
import java.util.UUID;

@Controller
@RequestMapping("/rest/v1")
public class AccessToeknRestController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthTokenService oauthTokenService;

    @Autowired
    private OauthClientService oauthClientService;

    @Autowired
    private RestAuthService restAuthService;

    @RequestMapping(value = "/token/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthAccessToken> getToken(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthAccessToken accessToken = oauthTokenService.selectTokenByGroupIdAndId(management.getId(), id);
            if (accessToken == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(accessToken, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<Void> createToken(HttpServletRequest request, @RequestBody OauthAccessToken oauthAccessToken, UriComponentsBuilder ucBuilder) {
        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Long clientId = oauthAccessToken.getClientId();
            OauthClient oauthClient = oauthClientService.selectByGroupIdAndId(management.getId(), clientId);
            if (oauthClient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            if (oauthClient.getRefreshTokenValidity().equals("Y")) {
                oauthAccessToken.setRefreshToken(UUID.randomUUID().toString());
            }
            oauthAccessToken.setGroupId(management.getId());
            oauthAccessToken.setToken(UUID.randomUUID().toString());

            OauthAccessToken createdToken = oauthTokenService.insertToken(oauthAccessToken);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/token/{id}").buildAndExpand(createdToken.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthAccessToken> updateToken(HttpServletRequest request, @PathVariable("id") long id, @RequestBody OauthAccessToken oauthAccessToken) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            OauthAccessToken currentToken = oauthTokenService.selectTokenByGroupIdAndId(management.getId(), id);

            if (currentToken == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            currentToken.setScopes(oauthAccessToken.getScopes());
            currentToken.setAdditionalInformation(oauthAccessToken.getAdditionalInformation());

            oauthTokenService.updateTokenById(currentToken);

            currentToken = oauthTokenService.selectTokenByGroupIdAndId(management.getId(), id);
            return new ResponseEntity<>(currentToken, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthAccessToken> deleteToken(HttpServletRequest request, @PathVariable("id") long id) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthAccessToken currentToken = oauthTokenService.selectTokenByGroupIdAndId(management.getId(), id);

            if (currentToken == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthTokenService.deleteTokenById(currentToken.getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/search/token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthAccessToken>> searchToken(HttpServletRequest request,
                                                              @RequestParam(required = false) String type,
                                                              @RequestParam(required = false) String scopes,
                                                              @RequestParam(required = false) String token,
                                                              @RequestParam(required = false) Long oauthUserId,
                                                              @RequestParam(required = false) Long clientId,
                                                              @RequestParam(required = false) String refreshToken,
                                                              @RequestParam(required = false) String additionalInformation
    ) {

        Management management = restAuthService.managementParser(request);
        if (management == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            OauthAccessToken accessToken = new OauthAccessToken();
            accessToken.setGroupId(management.getId());
            accessToken.setType(type);
            accessToken.setScopes(scopes);
            accessToken.setToken(token);
            accessToken.setOauthUserId(oauthUserId);
            accessToken.setClientId(clientId);
            accessToken.setRefreshToken(refreshToken);
            accessToken.setAdditionalInformation(additionalInformation);

            List<OauthAccessToken> oauthAccessTokens = oauthTokenService.selectTokenByCondition(accessToken);

            if (oauthAccessTokens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthAccessTokens, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
