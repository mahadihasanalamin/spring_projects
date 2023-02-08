package com.example.springsecurityclient.event.listener;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.ResendVerificationEmailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResendVerificationEmailListener implements ApplicationListener<ResendVerificationEmailEvent> {
    @Override
    public void onApplicationEvent(ResendVerificationEmailEvent event) {
        User user = event.getUser();
        String url = event.getApplicationUrl()+"/verifyRegistration?token="+event.getToken();
        log.info("click the link to verify your account {}", url);
    }
}
