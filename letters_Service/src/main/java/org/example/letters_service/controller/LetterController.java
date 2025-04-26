//package org.example.letters_service.controller;
//
//import org.example.letters_service.model.Letter;
//import org.example.letters_service.service.LetterService;
//import org.example.letters_service.config.JwtUtil;
//import org.example.letters_service.model.User;
//import org.example.letters_service.repository.UserRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/letters")
//public class LetterController {
//
//    @Autowired
//    private LetterService letterService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    // Extract user ID from JWT Token
//    private Long getUserIdFromToken(String authHeader) {
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new RuntimeException("Missing or invalid Authorization header");
//        }
//
//        String token = authHeader.substring(7); // Remove "Bearer " prefix
//        String email = jwtUtil.extractUsername(token); // Extract email from JWT
//
//        return userRepository.findByEmail(email)
//                .map(User::getId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    // Admin: Post a letter (JWT Protected)
//    @PostMapping("/post")
//    public ResponseEntity<String> postLetter(@RequestHeader("Authorization") String authHeader, @RequestBody Letter letter) {
//        Long userId = getUserIdFromToken(authHeader); // Validate user
//        // Additional validation for admin role can be added here
//
//        letterService.saveLetter(letter);
//        return ResponseEntity.ok("Letter posted successfully.");
//    }
//
//    // User: View all letters (JWT Protected)
//    @GetMapping("/all")
//    public ResponseEntity<List<Letter>> getAllLetters(@RequestHeader("Authorization") String authHeader) {
//        getUserIdFromToken(authHeader); // Validate user
//        return ResponseEntity.ok(letterService.getAllLetters());
//    }
//
//    // User: View letters by category (JWT Protected)
//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<Letter>> getLettersByCategory(@RequestHeader("Authorization") String authHeader, @PathVariable String category) {
//        getUserIdFromToken(authHeader); // Validate user
//        return ResponseEntity.ok(letterService.getLettersByCategory(category));
//    }
//    // LetterController.java
//    @GetMapping("/categories")
//    public ResponseEntity<List<String>> getAllCategories(@RequestHeader("Authorization") String authHeader) {
//        getUserIdFromToken(authHeader); // Validate user
//        return ResponseEntity.ok(letterService.getAllCategories());
//    }
//
//
//}
package org.example.letters_service.controller;

import org.example.letters_service.model.Letter;
import org.example.letters_service.service.LetterService;
// Removed: import org.example.letters_service.config.JwtUtil;
// Removed: import org.example.letters_service.model.User;
import org.example.letters_service.repository.UserRepository; // Keep if needed for role checks maybe?

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; // Import
import org.springframework.security.core.context.SecurityContextHolder; // Import
// Removed: import org.springframework.security.core.userdetails.UserDetails; // Only needed if accessing details directly
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letters") // Base path for all methods in this controller
public class LetterController {

    @Autowired
    private LetterService letterService;

    // Removed: @Autowired private JwtUtil jwtUtil;

    // Keep UserRepository only if needed for checks beyond authentication (e.g., roles not in JWT)
    @Autowired
    private UserRepository userRepository;

    // Helper method to get User ID from Security Context (Optional but good practice)
    // Throws exception if user somehow isn't found after successful authentication
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            // This case should ideally be caught by the .authenticated() rule in SecurityConfig
            throw new RuntimeException("User is not authenticated");
        }

        String email = authentication.getName(); // Principal's name is usually the username (email here)

        // You might not strictly need this check in every method if authentication implies existence,
        // but it's safer if user deletion is possible.
        return userRepository.findByEmail(email)
                .map(user -> user.getId()) // Use the User model from this service
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in repository: " + email));
    }

    // Admin: Post a letter (JWT Protected by Filter)
    // TODO: Add Role-based authorization check if needed (e.g., @PreAuthorize("hasRole('ADMIN')"))
    @PostMapping("/post")
    // Removed: @RequestHeader("Authorization") String authHeader
    public ResponseEntity<String> postLetter(@RequestBody Letter letter) {
        // Removed: Long userId = getUserIdFromToken(authHeader);
        // Optional: Call getCurrentUserId() if you need the ID for logging or other logic
        // getCurrentUserId();
        // TODO: Add logic here to check if the authenticated user has ADMIN role

        letterService.saveLetter(letter);
        return ResponseEntity.ok("Letter posted successfully.");
    }

    // User: View all letters (JWT Protected by Filter)
    @GetMapping("/all")
    // Removed: @RequestHeader("Authorization") String authHeader
    public ResponseEntity<List<Letter>> getAllLetters() {
        // Removed: getUserIdFromToken(authHeader);
        // Optional: getCurrentUserId(); // Validate user exists in DB if desired
        return ResponseEntity.ok(letterService.getAllLetters());
    }

    // User: View letters by category (JWT Protected by Filter)
    @GetMapping("/category/{category}")
    // Removed: @RequestHeader("Authorization") String authHeader
    public ResponseEntity<List<Letter>> getLettersByCategory(@PathVariable String category) {
        // Removed: getUserIdFromToken(authHeader);
        // Optional: getCurrentUserId();
        return ResponseEntity.ok(letterService.getLettersByCategory(category));
    }

    // Get distinct categories (JWT Protected by Filter)
    @GetMapping("/categories")
    // Removed: @RequestHeader("Authorization") String authHeader
    public ResponseEntity<List<String>> getAllCategories() {
        // Removed: getUserIdFromToken(authHeader);
        // Optional: getCurrentUserId();
        return ResponseEntity.ok(letterService.getAllCategories());
    }
}