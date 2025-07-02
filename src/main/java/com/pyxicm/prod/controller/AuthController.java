package com.pyxicm.prod.controller;

import com.pyxicm.prod.dto.*;
import com.pyxicm.prod.model.User;
import com.pyxicm.prod.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }

    @GetMapping("/me")
    public User getMe(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        return authService.getUserFromToken(token);
    }
}
