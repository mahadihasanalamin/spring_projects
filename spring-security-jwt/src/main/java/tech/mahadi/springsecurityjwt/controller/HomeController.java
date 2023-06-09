package tech.mahadi.springsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/demo")
public class HomeController {
    @GetMapping()
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("WELCOME HOME");
    }
}
