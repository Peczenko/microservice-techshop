package project.org.techshop.config.security;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import project.org.techshop.clients.UserClient;
import project.org.techshop.service.PaymentService;


@Component("customSecurity")
@RequiredArgsConstructor
public class CustomSecurity {
    private final UserClient userFeignClient;
    private final PaymentService paymentService;

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

    public boolean isUserOwnerByPaymentId(Long paymentId, Authentication authentication){
        String userId = paymentService.getUserIdByPaymentId(paymentId);
        return isUserOwner(userId, authentication);
    }
    public boolean isOrderService(Authentication authentication){
        if(authentication == null){
            return false;
        }

        Object principal = authentication.getPrincipal();
        if(principal instanceof Jwt jwt){
            return jwt.getClaims().get("azp").equals("order-service-client");
        }
        return false;
    }

}

