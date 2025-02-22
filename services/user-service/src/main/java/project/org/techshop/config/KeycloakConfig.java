package project.org.techshop.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Value("${app.keycloak.server-url}")
    private String serverUrl;
    @Value("${app.keycloak.admin.client-id}")
    private String clientId;
    @Value("${app.keycloak.admin.client-secret}")
    private String clientSecret;
    @Value("${app.keycloak.realm}")
    private String realm;


    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .realm(realm)
                .build();
    }
}
