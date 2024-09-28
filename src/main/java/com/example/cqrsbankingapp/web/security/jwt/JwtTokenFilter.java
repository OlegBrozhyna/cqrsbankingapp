package com.example.cqrsbankingapp.web.security.jwt;


import io.github.ilyalisov.jwt.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;


@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    // Injects the TokenService and UserDetailsService for handling JWT and user details
    private final TokenService jwtService;
    private final UserDetailsService userDetailsService;

    // This method intercepts the HTTP request and checks for a valid JWT token
    @Override
    @SneakyThrows
    public void doFilter(
            final ServletRequest req,
            final ServletResponse res,
            final FilterChain filterChain
    ) {
        try {
            // Extract the token from the Authorization header
            String token = resolve((HttpServletRequest) req);

            // If the token is valid and of type ACCESS, and is not expired
            if (!token.isEmpty()
                    && jwtService.getType(token).equals(TokenType.ACCESS.name())
                    && !jwtService.isExpired(token)) {

                // Get the authentication object from the token
                Authentication auth = getAuthentication(token);

                // Set the authentication in the SecurityContext if valid
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception ignored) {
            // Ignore exceptions to ensure the filter chain continues
        }

        // Continue the filter chain
        filterChain.doFilter(req, res);
    }

    // Resolves the JWT token from the Authorization header
    private String resolve(
            final HttpServletRequest request
    ) {
        String bearerToken = request.getHeader("Authorization");
        // Extract token if it starts with "Bearer "
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove the "Bearer " prefix
        }
        return "";
    }

    // Retrieves the authentication object based on the JWT token's subject (username)
    private Authentication getAuthentication(
            final String token
    ) {
        String subject = jwtService.getSubject(token); // Get the username from the token
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(subject); // Load the user details from the database

        // Return an authenticated token if user details are found
        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(
                    userDetails,
                    "",
                    userDetails.getAuthorities()); // Use the user's roles/authorities
        }
        return null; // Return null if user details not found
    }
}



