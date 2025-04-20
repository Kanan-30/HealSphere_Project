package org.example.login_Service.service;

import org.example.login_Service.dto.UserResponse;
import org.example.login_Service.dto.UserRequest;
import org.example.login_Service.entity.User;
import org.example.login_Service.helper.JwtUtil;
import org.example.login_Service.mapper.UserMapper;
import org.example.login_Service.repo.UserRepo;
import org.example.login_Service.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String createCustomer(UserRequest request) {
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepo.save(user);
        return "User created successfully";
    }

    public String login(LoginRequest request) {
        Optional<User> userOptional = userRepo.findByEmail(request.email());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.password(), user.getPassword())) {
                return jwtUtil.generateToken(user.getEmail());
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    public UserResponse getUserDetails(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toCustomerResponse(user);
    }


    public String validateAndExtractEmail(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }
        String token = authHeader.substring(7);

        if (!jwtUtil.isTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }
        return jwtUtil.extractEmail(token);
    }

}