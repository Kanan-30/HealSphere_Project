//package org.example.login_Service.helper;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
    //    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            String email = jwtUtil.extractEmail(token);
//
//            if (email != null && jwtUtil.isTokenValid(token)) {
//                // Add authentication context logic here
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
package org.example.self_discovery_service.helper;

// Import JwtUtil from the 'config' package
import org.example.self_discovery_service.config.JwtUtil;
// Import UserDetailsService implementation (assuming it's in 'service' package)
import org.example.self_discovery_service.service.UserDetailsServiceImpl;

// Necessary imports from Jakarta, Spring Security, Lombok, SLF4J, JWT library etc.
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException; // Ensure this is imported if caught below

import java.io.IOException;
// Removed unused import: import java.util.ArrayList; (if not used elsewhere)


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    // Inject dependencies
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail; // Using 'userEmail' as variable name, matches logic

        // 1. Check for Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extract token
        jwt = authHeader.substring(7);

        try {
            // 3. Extract username (email) from token
            userEmail = jwtUtil.extractUsername(jwt); // Assuming extractUsername gives the email

            // 4. Check if username exists and user is not already authenticated
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 5. Load UserDetails from database
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // 6. *** CORRECTED LINE ***
                // Validate the token using the actual method from JwtUtil.java
                if (jwtUtil.validateToken(jwt)) {
                    // 7. If token is valid, create authentication object
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,          // Principal
                            null,                 // Credentials (not needed for JWT)
                            userDetails.getAuthorities() // Authorities
                    );
                    // 8. Set details
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    // 9. Update SecurityContextHolder - Authenticate the user for this request
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("Successfully authenticated user '{}' via JWT.", userEmail);
                } else {
                    // This branch might be less likely if validateToken catches expiration/signature errors
                    log.warn("JWT token failed validation according to JwtUtil for user '{}'.", userEmail);
                }
            }
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            // Consider setting response status to 401 Unauthorized here if needed
            // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (SignatureException | MalformedJwtException e) {
            // Catch specific JWT errors if needed (ensure SignatureException is imported)
            log.warn("JWT token signature or format is invalid: {}", e.getMessage());
            // Consider setting response status to 401 Unauthorized here if needed
        } catch (UsernameNotFoundException e) {
            // This happens if loadUserByUsername fails
            log.warn("User details not found for email extracted from JWT: {}", e.getMessage());
        } catch (Exception e) {
            // Catch-all for other potential errors during JWT processing or authentication setup
            log.error("Could not set user authentication in security context", e);
        }

        // 10. Continue the filter chain
        filterChain.doFilter(request, response);
    }
}