package project.org.techshop.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    @GetMapping("{userId}/keycloak-id/{keycloakId}")
    ResponseEntity<Void> isRightUser(@PathVariable("userId") String userId, @PathVariable("keycloakId") String keycloakId);

}
