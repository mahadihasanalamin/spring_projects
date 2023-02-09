package com.example.springsecurityclient.service;

import com.example.springsecurityclient.entity.PasswordResetToken;
import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.entity.UserVerificationToken;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.repository.PasswordResetTokenRepository;
import com.example.springsecurityclient.repository.UserRepository;
import com.example.springsecurityclient.repository.UserVerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserVerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    //REGISTRATION AND VERIFY ACCOUNT
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPhone(userModel.getPhone());
        user.setRole("User");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(user);
    }

    public void saveVerificationToken(UserVerificationToken userVerificationToken) {
        verificationTokenRepository.save(userVerificationToken);
    }

    public String validateToken(String token) {
        UserVerificationToken userVerificationToken = verificationTokenRepository.findByToken(token);
        if(userVerificationToken == null) {
            return "Invalid";
        }
        Calendar calendar = Calendar.getInstance();
        if(userVerificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <=0){
            verificationTokenRepository.delete(userVerificationToken);
            return "Expired";
        }
        User user = userVerificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(userVerificationToken);
        return "Valid";
    }

    //RESET PASSWORD
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public void saveResetPasswordToken(String token, User user) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
    public String setNewPassword(String token, String newPassword){
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) return "Bad User!";
        Calendar calendar = Calendar.getInstance();
        if(passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0){
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Expired!";
        }
        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(passwordResetToken);

        return "Password Changed!!";
    }

    //CHANGE PASSWORD
    public boolean checkValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
