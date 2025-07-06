package com.pyxicm.prod.service;

import com.pyxicm.prod.dto.*;
import com.pyxicm.prod.model.User;
import com.pyxicm.prod.repository.UserRepository;
import com.pyxicm.prod.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void register(RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);
    }

    public String login(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Use userId in token now
        return jwtUtil.generateToken(user.getId());
    }

    public User getUserFromToken(String token) {
        // ✅ Extract userId instead of email
        String userId = jwtUtil.extractUserId(token);
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
