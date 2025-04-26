//package org.example.self_discovery_service.controller;
//
//import org.example.self_discovery_service.entity.*;
//import org.example.self_discovery_service.repository.*;
//import org.example.self_discovery_service.config.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/self-discovery")
//public class SelfDiscoveryController {
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private OptionRepository optionRepository;
//
//    @Autowired
//    private UserResponseRepository userResponseRepository;
//
//    @Autowired
//    private ReportRepository reportRepository;
//
//    @Autowired
//    private UserRepository userRepository; // ✅ Fixed incorrect repository injection
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
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
//
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    // Get Questions for a Step (JWT Protected)
//    @GetMapping("/questions/{step}")
//    public List<Question> getQuestions(@RequestHeader("Authorization") String authHeader, @PathVariable String step) {
//        getUserIdFromToken(authHeader); // Validate token
//        return questionRepository.findByStep(step);
//    }
//
//    // Get Options for a Question (JWT Protected)
//    @GetMapping("/options/{questionId}")
//    public List<Option> getOptions(@RequestHeader("Authorization") String authHeader, @PathVariable Long questionId) {
//        getUserIdFromToken(authHeader); // Validate token
//        return optionRepository.findByQuestionId(questionId);
//    }
//
//    // Save User Response (JWT Protected)
//    @PostMapping("/user-response")
//    public String saveUserResponse(@RequestHeader("Authorization") String authHeader, @RequestBody UserResponse userResponse) {
//        Long userId = getUserIdFromToken(authHeader);
//
//        if (userResponse.getQuestionId() == null) {
//            return "{\"error\": \"Question ID is required!\"}";
//        }
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        userResponse.setUser(user); // ✅ Correctly set the user object
//        userResponseRepository.save(userResponse);
//        return "{\"message\": \"Response saved successfully!\"}";
//    }
//
//    // Get User Report (JWT Protected)
//    @GetMapping("/report")
//    public List<Report> getReport(@RequestHeader("Authorization") String authHeader) {
//        Long userId = getUserIdFromToken(authHeader);
//
//        // Fetch all responses for the given userId
//        List<UserResponse> userResponses = userResponseRepository.findByUserId(userId);
//
//        // Fetch reports based on the user's responses
//        List<Report> reports = new ArrayList<>();
//        for (UserResponse response : userResponses) {
//            Optional<Report> report = reportRepository.findByQuestionIdAndSelectedOption(
//                    response.getQuestionId(),
//                    response.getSelectedOption()
//            );
//            report.ifPresent(reports::add);
//        }
//
//        return reports;
//    }
//}
package org.example.self_discovery_service.controller;

import org.example.self_discovery_service.entity.*;
import org.example.self_discovery_service.repository.*;
// Removed: import org.example.self_discovery_service.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication; // Import Authentication
import org.springframework.security.core.context.SecurityContextHolder; // Import SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails; // Import UserDetails if needed directly
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/self-discovery")
public class SelfDiscoveryController {

    // ... (Keep Autowired repositories) ...
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    // Removed: @Autowired private JwtUtil jwtUtil;

    // Helper method to get User ID from Security Context
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated"); // Should not happen if filter works
        }

        String email = authentication.getName(); // Principal's name is usually the username (email here)

        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in repository: " + email));
    }


    // Get Questions for a Step (JWT Protected by Filter)
    @GetMapping("/questions/{step}")
    // Removed: @RequestHeader("Authorization") String authHeader
    public List<Question> getQuestions(@PathVariable String step) {
        // Removed: getUserIdFromToken(authHeader); // Validation handled by JwtAuthenticationFilter
        getCurrentUserId(); // Optional: Still check if user exists in DB if needed, or just proceed
        return questionRepository.findByStep(step);
    }

    // Get Options for a Question (JWT Protected by Filter)
    @GetMapping("/options/{questionId}")
    // Removed: @RequestHeader("Authorization") String authHeader
    public List<Option> getOptions(@PathVariable Long questionId) {
        // Removed: getUserIdFromToken(authHeader);
        getCurrentUserId(); // Optional check
        return optionRepository.findByQuestionId(questionId);
    }

    // Save User Response (JWT Protected by Filter)
    @PostMapping("/user-response")
    // Removed: @RequestHeader("Authorization") String authHeader
    public String saveUserResponse(@RequestBody UserResponse userResponse) {
        Long userId = getCurrentUserId(); // Get ID from authenticated user

        if (userResponse.getQuestionId() == null) {
            // Consider returning a 400 Bad Request instead of just a string
            return "{\"error\": \"Question ID is required!\"}";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found for ID: " + userId)); // Should be rare

        userResponse.setUser(user);
        userResponseRepository.save(userResponse);
        return "{\"message\": \"Response saved successfully!\"}";
    }

    // Get User Report (JWT Protected by Filter)
    @GetMapping("/report")
    // Removed: @RequestHeader("Authorization") String authHeader
    public List<Report> getReport() {
        Long userId = getCurrentUserId(); // Get ID from authenticated user

        List<UserResponse> userResponses = userResponseRepository.findByUserId(userId);
        List<Report> reports = new ArrayList<>();
        for (UserResponse response : userResponses) {
            Optional<Report> report = reportRepository.findByQuestionIdAndSelectedOption(
                    response.getQuestionId(),
                    response.getSelectedOption()
            );
            report.ifPresent(reports::add);
        }
        return reports;
    }
}