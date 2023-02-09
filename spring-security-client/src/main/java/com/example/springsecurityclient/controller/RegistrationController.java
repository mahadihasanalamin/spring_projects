package com.example.springsecurityclient.controller;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.model.PasswordModel;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    //REGISTRATION AND VERIFY ACCOUNT
    @PostMapping("/user/registration")
    public String registerUser(@RequestBody UserModel userModel, HttpServletRequest request){
        User user = userService.registerUser(userModel);
        String url = applicationUrlGenerate(request);
        applicationEventPublisher.publishEvent(new RegistrationEvent(user, url));
        return "Successfully Registered!!";
    }
    private String applicationUrlGenerate(HttpServletRequest request) {
        String url = "http://"
                +request.getServerName()
                +":"
                +request.getServerPort()
                +request.getContextPath();
        return url;
    }
    @GetMapping("/verifyRegistration")
    public String VerifyRegistration(@RequestParam("token") String token){
        String result = userService.validateToken(token);
        return "Account Verified!!";
    }
}
