package com.example.springsecurityclient.controller;

import com.example.springsecurityclient.entity.PasswordResetToken;
import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.model.PasswordModel;
import com.example.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //RESET PASSWORD
    @PostMapping("/user/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(user == null) return "Bad User";
        String token = UUID.randomUUID().toString();
        userService.saveResetPasswordToken(token, user);
        String url = resetPasswordUrlGenerate(applicationUrlGenerate(request), token);
        return url;
    }
    private String applicationUrlGenerate(HttpServletRequest request) {
        String url = "http://"
                +request.getServerName()
                +":"
                +request.getServerPort()
                +request.getContextPath();
        return url;
    }
    private String resetPasswordUrlGenerate(String url, String token) {
        return url+"/setNewPassword?token="+token;
    }
    @PostMapping("/setNewPassword")
    public String setNewPassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel){
        String result = userService.setNewPassword(token, passwordModel.getNewPassword());
        return result;
    }

    //CHANGE PASSWORD
    @PostMapping("/user/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if(user == null) return "Bad User!";
        if(!userService.checkValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid Old Password!";
        }
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password Changed!!";
    }
}
