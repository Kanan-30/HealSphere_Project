package org.example.login_Service.controller;
import org.example.login_Service.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.example.login_Service.dto.UserRequest;
import org.example.login_Service.dto.UserResponse;
import org.example.login_Service.service.UserService;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")


public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(userService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserDetails(@RequestHeader("Authorization") String authHeader) {
        String email = userService.validateAndExtractEmail(authHeader);
        return ResponseEntity.ok(userService.getUserDetails(email));
    }

}




