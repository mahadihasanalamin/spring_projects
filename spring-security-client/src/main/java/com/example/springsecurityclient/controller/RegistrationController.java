package com.example.springsecurityclient.controller;

import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.entity.VerificationToken;
import com.example.springsecurityclient.event.RegistrationEvent;
import com.example.springsecurityclient.event.ResendVerificationEmailEvent;
import com.example.springsecurityclient.model.PasswordModel;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @PostMapping("/user/registration")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        applicationEventPublisher.publishEvent(new RegistrationEvent(user, applicationUrl(request)));
        return "User Successfully Registered!!";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToke(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified Successfully";
        } else{
            return "Bad User";
        }
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello to Spring Boot World!!";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationEmail(@RequestParam("token") String oldToken, HttpServletRequest request){
        VerificationToken verificationToken = userService.newVerificationToken(oldToken);
        User user = verificationToken.getUser();
        applicationEventPublisher.publishEvent(new ResendVerificationEmailEvent(user, applicationUrl(request), verificationToken.getToken()));

        return "Verification Link sent";
    }

    //RESET PASSWORD
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        System.out.println("USER DATA: "+user);
        if(user != null){
            String token = UUID.randomUUID().toString();
            userService.savePasswordToken(user, token);
            url = resetPasswordTokenMail(user, applicationUrl(request),token);
        }

        return url;
    }

    private String resetPasswordTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl+"/setNewPassword?token="+token;
        log.info("Click the link to reset your password {}",url);
        return url;
    }

    @PostMapping("/setNewPassword")
    public String setNewPassword(@RequestParam String token, @RequestBody PasswordModel passwordModel){
        User user = userService.validateResetPasswordToken(token);
        if(user != null){
            userService.resetPassword(user, passwordModel.getNewPassword(), token);
            return "Password Has Changed Successfully!!";
        }else {
            return "Invalid Token";
        }
    }

    //CHANGE PASSWORD
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(user == null) return "Invalid User";
        if(!userService.checkValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid Password";
        }
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password Has Successfully Changed!!";
    }
}
