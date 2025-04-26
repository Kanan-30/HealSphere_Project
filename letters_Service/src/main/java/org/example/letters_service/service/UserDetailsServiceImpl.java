package org.example.letters_service.service;

import lombok.RequiredArgsConstructor;
import org.example.letters_service.model.User;
import org.example.letters_service.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList; // Import for authorities list

@Service // Mark this class as a Spring service bean
@RequiredArgsConstructor // Inject dependencies via constructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository; // Inject your User repository

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the user in your database by email (which is used as the 'username' here)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert your User entity to Spring Security's UserDetails object.
        // Spring provides a convenient User builder.
        // You need to provide:
        // 1. Username (which is the email in your case)
        // 2. Password (the HASHED password from your database)
        // 3. Authorities (roles/permissions - create an empty list or assign defaults for now)
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>() // Provide an empty list of authorities for now
                // Or add actual authorities if you have roles:
                // Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        // --- OR ---
        // If your User entity implemented UserDetails directly, you could just return it:
        // return user; // (But your current User entity doesn't implement UserDetails)
    }
}