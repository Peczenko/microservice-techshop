package project.org.techshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.org.techshop.dto.*;
import project.org.techshop.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isUserOwner(#id, authentication)")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOrderService(authentication)")
    @GetMapping("/info-for-order/{id}")
    public ResponseEntity<UserInfoForOrder> getUserInfoForOrder(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserInfoForOrder(id));
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isAllowedService(authentication)")
    @GetMapping("{userId}/keycloak-id/{keycloakId}")
    public ResponseEntity<Void> checkCredentials(@PathVariable String userId, @PathVariable String keycloakId) {
        return userService.checkCredentials(userId, keycloakId) ? ResponseEntity.ok().build()   : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        userService.registerUser(userRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestParam String usernameOrEmail) {
        userService.forgotPassword(usernameOrEmail);
        return ResponseEntity.ok().build();
    }

}