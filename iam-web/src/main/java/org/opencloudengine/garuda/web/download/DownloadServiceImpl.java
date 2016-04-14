package org.opencloudengine.garuda.web.download;

import org.opencloudengine.garuda.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private DownloadRepository repository;

    @Autowired
    private MailService mailService;

    @Override
    public void sendDownloadMail(Download download) {

        String fromUser = config.getProperty("mail.contacts.address");
        String token = new String(Base64.encode(String.valueOf(System.currentTimeMillis()).getBytes()));
        download.setToken(token);

        repository.insert(download);
        mailService.download(download.getType(), download.getVersion(), token, "Download Link", fromUser, "Cloudine", download.getEmail(), download.getName(), null);
    }
}
