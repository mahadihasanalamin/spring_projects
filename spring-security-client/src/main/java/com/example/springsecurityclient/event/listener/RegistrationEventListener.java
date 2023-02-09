package com.example.springsecurityclient.event.listener;

import com.example.springsecurityclient.entity.UserVerificationToken;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        String token = UUID.randomUUID().toString();
        UserVerificationToken userVerificationToken = new UserVerificationToken(token, event.getUser());
        userService.saveVerificationToken(userVerificationToken);
        String url = event.getApplicationUrl()+"/verifyRegistration?token="+token;
        log.info("click the link for verify your account: "+url);
    }
}
