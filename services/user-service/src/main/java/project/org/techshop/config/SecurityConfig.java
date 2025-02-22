package project.org.techshop.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    @Order(0)
    public SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception {
        log.info("Entering order 1");
        http
                .securityMatcher("/api/v1/users/register", "/api/v1/users/register/**",
                        "/api/v1/users/verify-email/**",
                        "/api/v1/users/forgot-password/**", "/api/v1/users/login")
                .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable);
        log.info("Exiting order 1");
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain secureEndpoints(HttpSecurity http) throws Exception {
        log.info("Entering order 2");
        http
                .authorizeHttpRequests(authz ->
                        authz.anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable);
        log.info("Exiting order 2");
        return http.build();
    }
}
