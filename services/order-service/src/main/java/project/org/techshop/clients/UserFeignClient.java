package project.org.techshop.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.org.techshop.dto.UserOrderInfo;

import java.util.Optional;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserFeignClient {

    @GetMapping("info-for-order/{id}")
    Optional<UserOrderInfo> getUserInfoForOrder(@PathVariable("id") String id);

    @GetMapping("{userId}/keycloak-id/{keycloakId}")
    ResponseEntity<Void> isRightUser(@PathVariable("userId") String userId, @PathVariable("keycloakId") String keycloakId);

}

