package project.org.techshop.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FeignClientConfig {
    private final Keycloak keycloak;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String accessToken = keycloak.tokenManager().getAccessTokenString();
            log.info("Access token: {}", accessToken);
            requestTemplate.header("Authorization", "Bearer " + accessToken);
        };
    }
}
