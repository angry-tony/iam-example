package org.opencloudengine.garuda.web.console.oauthscope;


import org.opencloudengine.garuda.util.ExceptionUtils;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
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
public class OauthScopeRestController {
    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private OauthScopeService oauthScopeService;

    @Autowired
    private RestAuthService restAuthService;

    @RequestMapping(value = "/scope", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthScope>> listAllScopes(HttpServletRequest request, HttpServletResponse response) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            List<OauthScope> oauthScopes = oauthScopeService.selectAllByManagementId(management.get_id());
            if (oauthScopes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(oauthScopes, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope/pagination", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthScope>> pagination(HttpServletRequest request,HttpServletResponse response,
                                                       @RequestParam(defaultValue = "0") int offset,
                                                       @RequestParam(defaultValue = "100") int limit) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            //총합
            Long max = oauthScopeService.countAllByManagementId(management.get_id());

            //컨디션 합
            Long total = max;

            //컨디션 결과
            List<OauthScope> oauthScopes = oauthScopeService.selectByManagementId(management.get_id(), limit, new Long(offset));

            HttpHeaders headers = new HttpHeaders();
            headers.add("x-uengine-pagination-maxnbrecords", max + "");
            headers.add("x-uengine-pagination-currentoffset", offset + "");
            headers.add("x-uengine-pagination-totalnbrecords", total + "");

            return new ResponseEntity<>(oauthScopes, headers, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope/search/{searchKey}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<OauthScope>> search(HttpServletRequest request,HttpServletResponse response,
                                                   @PathVariable("searchKey") String searchKey,
                                                   @RequestParam(defaultValue = "0") int offset,
                                                   @RequestParam(defaultValue = "100") int limit) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //총합
            Long max = oauthScopeService.countAllByManagementId(management.get_id());

            //컨디션 합
            Long total = oauthScopeService.countAllByManagementIdLikeScopeName(management.get_id(), searchKey);

            //컨디션 결과
            List<OauthScope> oauthScopes = oauthScopeService.selectByManagementLikeScopeName(management.get_id(), searchKey, limit, new Long(offset));

            HttpHeaders headers = new HttpHeaders();
            headers.add("x-uengine-pagination-maxnbrecords", max + "");
            headers.add("x-uengine-pagination-currentoffset", offset + "");
            headers.add("x-uengine-pagination-totalnbrecords", total + "");

            return new ResponseEntity<>(oauthScopes, headers, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<OauthScope> getScope(HttpServletRequest request,HttpServletResponse response,
                                               @PathVariable("id") String id) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthScope oauthScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), id);
            if (oauthScope == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(oauthScope, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope", method = RequestMethod.POST)
    public ResponseEntity<Void> createScope(HttpServletRequest request,HttpServletResponse response,
                                            @RequestBody OauthScope oauthScope, UriComponentsBuilder ucBuilder) {
        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            OauthScope existScope = oauthScopeService.selectByManagementIdAndName(management.get_id(), oauthScope.getName());
            if (existScope != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OauthScope createdScope = oauthScopeService.createScope(management.get_id(), oauthScope.getName(), oauthScope.getDescription());

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/rest/v1/scope/{_id}").buildAndExpand(createdScope.get_id()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope/{_id}", method = RequestMethod.PUT)
    public ResponseEntity<OauthScope> updateScope(HttpServletRequest request,HttpServletResponse response,
                                                  @PathVariable("_id") String _id, @RequestBody OauthScope oauthScope) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthScope currentScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentScope == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            oauthScope.set_id(currentScope.get_id());
            oauthScopeService.updateById(oauthScope);

            currentScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);
            return new ResponseEntity<>(currentScope, HttpStatus.OK);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }

    @RequestMapping(value = "/scope/{_id}", method = RequestMethod.DELETE)
    public ResponseEntity<OauthScope> deleteScope(HttpServletRequest request,HttpServletResponse response,
                                                  @PathVariable("_id") String _id) {

        try {
            Management management = restAuthService.managementParser(request);
            if (management == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            OauthScope currentScope = oauthScopeService.selectByManagementIdAndId(management.get_id(), _id);

            if (currentScope == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            oauthScopeService.deleteById(currentScope.get_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            ExceptionUtils.httpExceptionResponse(ex, response);
            return null;
        }
    }
}
