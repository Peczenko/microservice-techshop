package project.org.techshop.service;


import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor

public class RoleService {
    private final UserService userService;
    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;


    public void assignRole(String userId, String roleName) {
        UserResource user = userService.getUser(userId);

        RolesResource rolesResource = getRolesResource();
        RoleRepresentation role = rolesResource.get(roleName).toRepresentation();

        user.roles().realmLevel().add(Collections.singletonList(role));
    }

    private RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }
}
