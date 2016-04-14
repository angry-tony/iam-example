package org.opencloudengine.garuda.web.download;

import org.opencloudengine.garuda.common.rest.Response;
import org.opencloudengine.garuda.util.DateUtils;
import org.opencloudengine.garuda.util.ExceptionUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private DownloadService downloadService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity get(HttpServletResponse res, @RequestParam String type, @RequestParam String version, @RequestParam String token) {
        long tokenTimestamp = Long.parseLong(new String(Base64.decodeBase64(token)));
        if (DateUtils.getDiffDays(new Date(), new Date(tokenTimestamp)) > 1) {
            new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        InputStream is = null;
        try {
            String filename = MessageFormatter.format("{}-{}.zip", type, version).getMessage();
            File file = new File(config.getProperty("repository.path"), filename);

            if (!file.exists()) {
                return new ResponseEntity("File Not Found - " + filename, HttpStatus.NOT_FOUND);
            }

            is = new FileInputStream(file);
            res.setHeader("Content-Length", "" + file.length());
            res.setHeader("Content-Transfer-Encoding", "binary");
            res.setHeader("Content-Type", "application/force-download");
            res.setHeader("Content-Disposition", MessageFormatter.format("attachment; filePath={}; filename={}",
                    URLEncoder.encode(filename, "UTF-8"), URLEncoder.encode(filename, "UTF-8")).getMessage());
            res.setStatus(200);

            FileCopyUtils.copy(is, res.getOutputStream());
            res.flushBuffer();
            is.close();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (is != null) {
                IOUtils.closeQuietly(is);
            }
        }
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response request(@RequestBody Map params) {
        Response response = new Response();
        try {
            //String recaptcha = params.get("recaptcha").toString();
            //Recaptcha.validate(recaptcha, config.getProperty("captcha.url"), config.getProperty("captcha.secretkey"));
            Download download = new Download();
            download.setName(params.get("username").toString());
            download.setCompany(params.get("company").toString());
            download.setEmail(params.get("email").toString());
            download.setType(params.get("type").toString());
            download.setVersion(params.get("version").toString());

            downloadService.sendDownloadMail(download);
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
