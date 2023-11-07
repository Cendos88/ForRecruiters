package com.myapp.georgewannabe.controllers;

import com.myapp.georgewannabe.models.LoginRequest;
import com.myapp.georgewannabe.models.RegisterRequest;
import com.myapp.georgewannabe.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.status(200).body(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.status(200).body(authenticationService.login(request));
    }

}
