package project.org.techshop.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import project.org.techshop.service.UserService;

@Component("customSecurity")
@RequiredArgsConstructor
@Slf4j
public class CustomSecurity {
    private final UserService userService;

    public boolean isUserOwner(String userId, Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            String keycloakId = jwt.getSubject();
            return userService.getUserId(keycloakId).equals(userId);
        }
        return false;
    }

    public boolean isAllowedService(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt jwt) {
            return jwt.getClaim("azp").equals("order-service-client") || jwt.getClaim("azp").equals("payment-service-client");
        }
        return false;
    }

    public boolean isOrderService(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            log.info("authentication is null");
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt jwt) {
            log.info("azp: {}", jwt.getClaim("azp").toString());
            return jwt.getClaim("azp").equals("order-service-client");
        }
        return false;
    }


}
