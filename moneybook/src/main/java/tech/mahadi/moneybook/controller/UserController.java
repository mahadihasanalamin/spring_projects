package tech.mahadi.moneybook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.mahadi.moneybook.entity.User;
import tech.mahadi.moneybook.model.Registration;
import tech.mahadi.moneybook.model.SignIn;
import tech.mahadi.moneybook.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moneybook/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Registration registrationRequest){
        return ResponseEntity.ok(userService.signup(registrationRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody SignIn signIn){
        return authenticate(signIn);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody SignIn authenticationRequest){
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }
}
