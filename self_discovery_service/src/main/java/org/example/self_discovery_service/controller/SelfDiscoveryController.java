package org.example.self_discovery_service.controller;

import org.example.self_discovery_service.entity.*;
import org.example.self_discovery_service.repository.*;
import org.example.self_discovery_service.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/self-discovery")
public class SelfDiscoveryController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository; // ✅ Fixed incorrect repository injection

    @Autowired
    private JwtUtil jwtUtil;

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

    // Get Questions for a Step (JWT Protected)
    @GetMapping("/questions/{step}")
    public List<Question> getQuestions(@RequestHeader("Authorization") String authHeader, @PathVariable String step) {
        getUserIdFromToken(authHeader); // Validate token
        return questionRepository.findByStep(step);
    }

    // Get Options for a Question (JWT Protected)
    @GetMapping("/options/{questionId}")
    public List<Option> getOptions(@RequestHeader("Authorization") String authHeader, @PathVariable Long questionId) {
        getUserIdFromToken(authHeader); // Validate token
        return optionRepository.findByQuestionId(questionId);
    }

    // Save User Response (JWT Protected)
    @PostMapping("/user-response")
    public String saveUserResponse(@RequestHeader("Authorization") String authHeader, @RequestBody UserResponse userResponse) {
        Long userId = getUserIdFromToken(authHeader);

        if (userResponse.getQuestionId() == null) {
            return "{\"error\": \"Question ID is required!\"}";
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userResponse.setUser(user); // ✅ Correctly set the user object
        userResponseRepository.save(userResponse);
        return "{\"message\": \"Response saved successfully!\"}";
    }

    // Get User Report (JWT Protected)
    @GetMapping("/report")
    public List<Report> getReport(@RequestHeader("Authorization") String authHeader) {
        Long userId = getUserIdFromToken(authHeader);

        // Fetch all responses for the given userId
        List<UserResponse> userResponses = userResponseRepository.findByUserId(userId);

        // Fetch reports based on the user's responses
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
