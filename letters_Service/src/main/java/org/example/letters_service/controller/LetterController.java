package org.example.letters_service.controller;

import org.example.letters_service.model.Letter;
import org.example.letters_service.service.LetterService;
import org.example.letters_service.config.JwtUtil;
import org.example.letters_service.model.User;
import org.example.letters_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letters")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // Extract user ID from JWT Token
    private Long getUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        String email = jwtUtil.extractUsername(token); // Extract email from JWT

        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Admin: Post a letter (JWT Protected)
    @PostMapping("/post")
    public ResponseEntity<String> postLetter(@RequestHeader("Authorization") String authHeader, @RequestBody Letter letter) {
        Long userId = getUserIdFromToken(authHeader); // Validate user
        // Additional validation for admin role can be added here

        letterService.saveLetter(letter);
        return ResponseEntity.ok("Letter posted successfully.");
    }

    // User: View all letters (JWT Protected)
    @GetMapping("/all")
    public ResponseEntity<List<Letter>> getAllLetters(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate user
        return ResponseEntity.ok(letterService.getAllLetters());
    }

    // User: View letters by category (JWT Protected)
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Letter>> getLettersByCategory(@RequestHeader("Authorization") String authHeader, @PathVariable String category) {
        getUserIdFromToken(authHeader); // Validate user
        return ResponseEntity.ok(letterService.getLettersByCategory(category));
    }
    // LetterController.java
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate user
        return ResponseEntity.ok(letterService.getAllCategories());
    }


}
