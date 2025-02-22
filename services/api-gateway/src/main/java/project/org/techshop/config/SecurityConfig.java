package project.org.techshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    @Order(0)
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http){
        http
                .securityMatcher(ServerWebExchangeMatchers.pathMatchers(
                        "/api/v1/users/register/**",
                        "/api/v1/users/verify-email/**",
                        "/api/v1/users/forgot-password/**",
                        "/api/v1/users/login"))
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().permitAll())
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityWebFilterChain secureEndpoints(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
