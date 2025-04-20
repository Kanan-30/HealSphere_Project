package com.example.selfhelp.Controller;

import com.example.selfhelp.Entity.*;
import com.example.selfhelp.Services.SelfHelpService;
import com.example.selfhelp.Repo.UserRepository;
import com.example.selfhelp.SecurityConfig.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selfhelp")
public class SelfHelpController {

    @Autowired
    private SelfHelpService selfHelpService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    // Get All Toolkits (JWT Protected)
    @GetMapping("/toolkits")
    public List<SelfHelpToolkit> getAllToolkits(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate user
        return selfHelpService.getAllToolkits();
    }

    // Get Levels by Toolkit ID (JWT Protected)
    @GetMapping("/levels/{toolkitId}")
    public List<SelfHelpLevel> getLevels(@RequestHeader("Authorization") String authHeader, @PathVariable Long toolkitId) {
        getUserIdFromToken(authHeader); // Validate user
        return selfHelpService.getLevelsByToolkit(toolkitId);
    }

    // Get Content by Level ID (JWT Protected)
    @GetMapping("/content/{levelId}")
    public List<SelfHelpContent> getContent(@RequestHeader("Authorization") String authHeader, @PathVariable Long levelId) {
        getUserIdFromToken(authHeader); // Validate user
        return selfHelpService.getContentByLevel(levelId);
    }

    // Get Assessments by Level ID (JWT Protected)
    @GetMapping("/assessments/{levelId}")
    public List<SelfHelpAssessment> getAssessments(@RequestHeader("Authorization") String authHeader, @PathVariable Long levelId) {
        getUserIdFromToken(authHeader); // Validate user
        return selfHelpService.getAssessmentsByLevel(levelId);
    }

    // Update User Progress (JWT Protected)
    @PostMapping("/progress")
    public String updateProgress(@RequestHeader("Authorization") String authHeader, @RequestBody SelfHelpUserProgress progress) {
        Long userId = getUserIdFromToken(authHeader);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        progress.setUser(user); // Set user reference
        selfHelpService.updateUserProgress(progress);

        return "{\"message\": \"Progress Updated Successfully\"}";
    }
    @GetMapping("/progress")
    public List<SelfHelpUserProgress> getUserProgress(@RequestHeader("Authorization") String authHeader) {
        Long userId = getUserIdFromToken(authHeader);
        return selfHelpService.getProgressByUser(userId);
    }

}
