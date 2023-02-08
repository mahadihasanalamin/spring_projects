package com.example.springsecurityclient.service;

import com.example.springsecurityclient.entity.ResetPasswordToken;
import com.example.springsecurityclient.entity.User;
import com.example.springsecurityclient.entity.VerificationToken;
import com.example.springsecurityclient.model.UserModel;
import com.example.springsecurityclient.repository.ResetPasswordTokenRepository;
import com.example.springsecurityclient.repository.UserRepository;
import com.example.springsecurityclient.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    public void saveVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public String validateVerificationToke(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null) return "invalid";

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <=0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
//        verificationTokenRepository.delete(verificationToken);
        return "valid";
    }

    public VerificationToken newVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    //RESET PASSWORD
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void savePasswordToken(User user, String token) {
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(user, token);
        resetPasswordTokenRepository.save(resetPasswordToken);
    }

    public User validateResetPasswordToken(String token) {
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);
        if(resetPasswordToken == null) return null;
        Calendar calendar = Calendar.getInstance();
        if(resetPasswordToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            resetPasswordTokenRepository.delete(resetPasswordToken);
            return null;
        }
        return resetPasswordToken.getUser();
    }

    public void resetPassword(User user, String newPassword, String token) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);
        resetPasswordTokenRepository.delete(resetPasswordToken);
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
