//package org.example.login_Service.helper;
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
//                        .requestMatchers("/api/v1/user/register", "/api/v1/user/login").permitAll() // Adjust paths per service if needed
//                        // Catch-all for authentication
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
//
//package org.example.login_Service.helper; // Ensure this matches your package structure
package org.example.login_Service.helper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// Removed unused AuthenticationProvider import if you weren't using it directly here
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Import this
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Import this

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Use Lombok for constructor injection
public class SecurityConfig {

    // Inject the custom JWT filter
    private final JwtAuthenticationFilter jwtAuthFilter;
    // Inject your AuthenticationProvider if you have custom authentication logic beyond UserDetailsService
    // private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(auth -> auth
                        // Permit access to actuator health/info endpoints without authentication
                        .requestMatchers(EndpointRequest.to("health", "info")).permitAll()
                        // Permit access to registration and login endpoints without authentication
                        .requestMatchers("/api/v1/user/register", "/api/v1/user/login").permitAll()
                        // Require authentication for any other request
                        .anyRequest().authenticated()
                )
                // Configure session management to be stateless; session won't be used to store user's state.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Add the JWT authentication filter before the standard UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // If you have a custom AuthenticationProvider, you would typically add it here:
        // .authenticationProvider(authenticationProvider) // Uncomment and inject if needed

        return http.build();
    }

    // Bean for the Authentication Manager (needed for the login endpoint potentially)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // The PasswordEncoder bean is correctly defined in your AppConfig.java
}