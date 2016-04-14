package org.opencloudengine.garuda.web.req;

import org.opencloudengine.garuda.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ReqServiceImpl implements ReqService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private ReqRepository repository;

    @Autowired
    private MailService mailService;

    @Override
    public void sendReqMail(Req req) {
        repository.insert(req);
        String toUser = config.getProperty("mail.contacts.address");
        String subject = "["+req.getType()+" 라이센스 문의]" + req.getSubject();
        mailService.sendBySmtp(subject, req.getMessage(), req.getEmail(), req.getName(), toUser, req.getTelephone(), null);
    }
}
