package org.opencloudengine.garuda.web.req;

import org.opencloudengine.garuda.common.rest.Response;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/req")
public class ReqController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private ReqService reqService;


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response request(@RequestBody Map params) {
        Response response = new Response();
        try {
            //String recaptcha = params.get("recaptcha").toString();
            //Recaptcha.validate(recaptcha, config.getProperty("captcha.url"), config.getProperty("captcha.secretkey"));
            Req req = new Req();
            req.setName(params.get("username").toString());
            req.setTelephone(params.get("telephone").toString());
            req.setCustomer(params.get("customer").toString());
            req.setEmail(params.get("email").toString());
            req.setType(params.get("type").toString());
            req.setSubject(params.get("subject").toString());
            req.setMessage(params.get("message").toString());

            reqService.sendReqMail(req);
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.getError().setMessage(ex.getMessage());
            if (ex.getCause() != null) response.getError().setCause(ex.getCause().getMessage());
            response.getError().setException(ExceptionUtils.getFullStackTrace(ex));
        }
        return response;
    }
}
