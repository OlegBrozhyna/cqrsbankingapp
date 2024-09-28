package com.example.cqrsbankingapp.web.security;

import com.example.cqrsbankingapp.web.security.jwt.JwtProperties;

import com.example.cqrsbankingapp.web.security.jwt.JwtTokenFilter;
import io.github.ilyalisov.jwt.service.TokenService;
import io.github.ilyalisov.jwt.service.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Injects properties related to JWT and user details service
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;

    // Bean for password encoding using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean to initialize the TokenService with the JWT secret key
    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl(jwtProperties.getSecret());
    }

    // Bean to configure the AuthenticationManager for authentication handling
    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration configuration
    ) {
        return configuration.getAuthenticationManager();
    }

    // Bean to configure security filters and settings
    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(final HttpSecurity security) {
        security
                // Disable CSRF, CORS, and HTTP Basic authentication
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // Stateless session management (JWT-based authentication)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )

                // Customize how authentication and access errors are handled
                .exceptionHandling(configurer ->
                        configurer.authenticationEntryPoint(
                                        (request, response, exception) -> {
                                            // Return 401 Unauthorized for unauthenticated access
                                            response.setStatus(
                                                    HttpStatus.UNAUTHORIZED
                                                            .value()
                                            );
                                            response.getWriter()
                                                    .write("Unauthorized.");
                                        })
                                .accessDeniedHandler(
                                        (request, response, exception) -> {
                                            // Return 403 Forbidden for unauthorized access
                                            response.setStatus(
                                                    HttpStatus.FORBIDDEN
                                                            .value()
                                            );
                                            response.getWriter()
                                                    .write("Forbidden.");
                                        }))

                // Allow public access to authentication endpoints
                // All other requests require authentication
                .authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/api/v*/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                // Disable anonymous authentication
                .anonymous(AbstractHttpConfigurer::disable)

                // Add custom JWT token filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtTokenFilter(
                                tokenService(),
                                userDetailsService
                        ),
                        UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }
}
