package org.opencloudengine.garuda.web.registe;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.mail.MailService;
import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.web.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class RegisteServiceImpl implements RegisteService {

    @Autowired
    @Qualifier("config")
    private Properties config;

    @Autowired
    private RegisteRepository registeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;


    @Override
    public void sendRegisteMail(String email) {

        User user = userRepository.selectByUserEmail(email);
        Registe registe = new Registe();
        registe.setUser_id(user.getId());

        String fromUser = config.getProperty("mail.contacts.address");
        String token = new String(Base64.encode(String.valueOf(System.currentTimeMillis()).getBytes()));
        registe.setToken(token);

        registeRepository.insert(registe);
        mailService.registe(registe.getUser_id(), token, "Confirm Registration", fromUser, "Cloudine", email, null);
    }

    @Override
    public void completeRegiste(String user_id, String token) {
        Registe registe = new Registe();
        registe.setUser_id(Long.parseLong(user_id));
        registe.setToken(token);
        Registe managedRegiste = registeRepository.selectByUserEmail(registe);
        if(managedRegiste == null) throw new ServiceException("가입 확인 처리할 대상이 없습니다.");

        User user = userRepository.selectByUserId(registe.getUser_id());
        userRepository.updateByAck(user.getEmail());

        try{
            //zenService.upgradeZendeskUser( user.getEmail() );
        }catch (Exception ex){} //별다른 작업을 하지 않아도 된다.
    }
}
