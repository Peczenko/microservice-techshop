package project.org.techshop.config.security;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import project.org.techshop.clients.UserFeignClient;
import project.org.techshop.service.OrderService;


@Component("customSecurity")
@RequiredArgsConstructor
public class CustomSecurity {
    private final UserFeignClient userFeignClient;
    private final OrderService orderService;

    public boolean isUserOwner(String userId, Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt jwt) {
            String keycloakId = jwt.getSubject();
            try {
                return userFeignClient.isRightUser(userId, keycloakId).getStatusCode().is2xxSuccessful();
            } catch (FeignException e) {
                return false;
            }
        }
        return false;
    }
    public boolean isUserOwnerByOrderId(Long orderId, Authentication authentication){
        String userId = orderService.getOrderEntityById(orderId).getCustomerId();
        return isUserOwner(userId, authentication);
    }
}

