package project.org.techshop.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {
    private final Keycloak keycloak;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String accessToken = keycloak.tokenManager().getAccessTokenString();
            requestTemplate.header("Authorization", "Bearer " + accessToken);
        };
    }
}
