//package org.example.self_discovery_service.config;
//
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest; // Import this
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // ADD THIS RULE: Allow unauthenticated access to the health endpoint
//                        .requestMatchers(EndpointRequest.to("health")).permitAll()
//                        // Keep your existing rules
//                        .requestMatchers("/api/auth/**").permitAll()// Adjust paths per service if needed
//                        .requestMatchers("/api/self-discovery/**").permitAll()
//                        // Catch-all for authentication
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
package org.example.self_discovery_service.config;

import lombok.RequiredArgsConstructor;
import org.example.self_discovery_service.helper.JwtAuthenticationFilter; // Import your filter
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager; // Keep if needed elsewhere
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Import stateless policy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Keep if needed
import org.springframework.security.crypto.password.PasswordEncoder;    // Keep if needed
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Import this

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Add constructor injection
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter; // Inject the JWT filter

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // *** CHANGE THIS: Require authentication ***
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(EndpointRequest.to("health", "info")).permitAll() // Allow health checks
                        // .requestMatchers("/api/auth/**").permitAll() // Remove or secure if needed
                        .requestMatchers("/api/self-discovery/**").authenticated() // Secure your service endpoints
                        .anyRequest().authenticated() // Keep or adjust as needed
                )
                // *** ADD STATELESS SESSION MANAGEMENT ***
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // *** ADD THE JWT FILTER ***
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Remove PasswordEncoder if not used directly in this service
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Remove AuthManager if not used directly (e.g., for local password login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}