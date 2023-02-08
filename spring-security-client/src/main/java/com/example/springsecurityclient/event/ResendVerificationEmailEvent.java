package com.example.springsecurityclient.event;

import com.example.springsecurityclient.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class ResendVerificationEmailEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    private String token;
    public ResendVerificationEmailEvent(User user, String applicationUrl, String token) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.token = token;
    }
}
