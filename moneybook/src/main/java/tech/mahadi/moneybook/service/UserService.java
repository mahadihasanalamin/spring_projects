package tech.mahadi.moneybook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.mahadi.moneybook.entity.Token;
import tech.mahadi.moneybook.entity.User;
import tech.mahadi.moneybook.enumeration.Role;
import tech.mahadi.moneybook.filter.JwtService;
import tech.mahadi.moneybook.model.AuthenticationResponse;
import tech.mahadi.moneybook.model.Registration;
import tech.mahadi.moneybook.model.SignIn;
import tech.mahadi.moneybook.repository.TokenRepository;
import tech.mahadi.moneybook.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public String signup(Registration registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());
        user.setEmail(registerRequest.getEmail());
        if(registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }
        user.setRole(Role.admin);
        userRepository.save(user);
        return "User Successfully Saved!";
    }
    public String authenticate(SignIn authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception exception){
            return "Username or Password is not valid!";
        }
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User doesn't exist!"));

        if(tokenRepository.existsByUserId(user.getId())){
            tokenRepository.deleteByUserId(user.getId());
        }
        String jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .token(jwtToken)
                .user(user)
                .build();
        tokenRepository.save(token);
        return "Token: "+jwtToken;
    }
}
