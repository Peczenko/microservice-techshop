package project.org.techshop.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;



@Component("customSecurity")
@RequiredArgsConstructor
@Slf4j
public class CustomSecurity {

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

