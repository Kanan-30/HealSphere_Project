package org.example.coping_with_services.controller;

import org.example.coping_with_services.model.CrisisDescription;
import org.example.coping_with_services.model.CrisisResponseStep;
import org.example.coping_with_services.model.UserCrisisPlan;
import org.example.coping_with_services.repositories.UserCrisisPlanRepository;
import org.example.coping_with_services.services.CrisisDescriptionService;
import org.example.coping_with_services.services.CrisisResponseService;
import org.example.coping_with_services.config.JwtUtil;
import org.example.coping_with_services.model.User;
import org.example.coping_with_services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crisis")
public class CrisisController {

    @Autowired
    private CrisisDescriptionService crisisDescriptionService;

    @Autowired
    private CrisisResponseService crisisResponseService;

    @Autowired
    private UserCrisisPlanRepository userCrisisPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private Long getUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or Invalid Authorization Header");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        String email = jwtUtil.extractUsername(token); // Extract email from JWT

        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/descriptions")
    public List<CrisisDescription> getCrisisDescriptions(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate token
        return crisisDescriptionService.getCrisisDescriptions();
    }

    @GetMapping("/steps")
    public List<CrisisResponseStep> getCrisisSteps(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate token
        return crisisResponseService.getCrisisSteps();
    }

    @PostMapping("/save-plan")
    public ResponseEntity<String> saveCrisisPlan(@RequestHeader("Authorization") String authHeader, @RequestBody UserCrisisPlan plan) {
        Long userId = getUserIdFromToken(authHeader);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

//        plan.setUser(user); // Associate plan with the user
        crisisResponseService.saveUserCrisisPlan(plan);
        return ResponseEntity.ok("Crisis Plan Saved");
    }

    @GetMapping("/get-plan")
    public ResponseEntity<UserCrisisPlan> getUserCrisisPlan(@RequestHeader("Authorization") String authHeader) {
        Long userId = getUserIdFromToken(authHeader);
        return userCrisisPlanRepository.findByUser_Id(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
