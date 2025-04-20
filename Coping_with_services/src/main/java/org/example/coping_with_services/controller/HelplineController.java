package org.example.coping_with_services.controller;

import org.example.coping_with_services.model.Helpline;
import org.example.coping_with_services.config.JwtUtil;
import org.example.coping_with_services.services.HelplineService;
import org.example.coping_with_services.model.User;
import org.example.coping_with_services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helplines")
public class HelplineController {

    @Autowired
    private HelplineService helplineService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping
    public List<Helpline> getHelplines(@RequestHeader("Authorization") String authHeader) {
        getUserIdFromToken(authHeader); // Validate token
        return helplineService.getAllHelplines();
    }
}
