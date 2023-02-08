package com.example.springsecurityclient.event.listener;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j //To print the logger
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        //Create Verification token for the user
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        //Store the user and token into database
        userService.saveVerificationToken(user, token);

        //Create the url
        String url = event.getApplicationUrl()+"/verifyRegistration?token="+token;

        //send the email (but here we just print the email in console)
        log.info("click the link to verify your account: {}",url);
    }
}
