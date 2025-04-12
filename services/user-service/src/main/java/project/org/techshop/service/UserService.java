package project.org.techshop.service;

import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import project.org.techshop.dto.*;
import project.org.techshop.entity.User;
import project.org.techshop.exception.CustomerNotFoundException;
import project.org.techshop.exception.UserRegistrationException;
import project.org.techshop.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Keycloak keycloak;

    @Value("${app.keycloak.server-url}")
    private String serverUrl;
    @Value("${app.keycloak.admin.client-id}")
    private String clientId;
    @Value("${app.keycloak.admin.client-secret}")
    private String clientSecret;
    @Value("${app.keycloak.realm}")
    private String realm;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userRepository.save(userMapper.toUser(userRequest));
        return userMapper.toUserResponse(user);
    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("User not found in userService"));
        return userMapper.toUserResponse(user);
    }

    public UserInfoForOrder getUserInfoForOrder(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        return userMapper.toUserInfoForOrder(user);
    }

    public void registerUser(UserRegistrationRequest userRegistrationRequest) {

        if (userRepository.existsByUsername(userRegistrationRequest.getUsername())) {
            throw new UserRegistrationException("User with this username already exists");
        }

        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new UserRegistrationException("User with this email already exists");
        }

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(userRegistrationRequest.getUsername());
        userRepresentation.setEmail(userRegistrationRequest.getEmail());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setFirstName(userRegistrationRequest.getFirstName());
        userRepresentation.setLastName(userRegistrationRequest.getLastName());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRequest.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() != 201) {
            log.info("STATUS: " + response.getStatus());
            log.info("STATUS INFO: " + response.getStatusInfo());
            throw new UserRegistrationException(response.getStatus() + " " + response.getStatusInfo());
        }

        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userRegistrationRequest.getUsername(), true);
        if (userRepresentations.isEmpty()) {
            throw new UserRegistrationException("User is not created");
        }

        userRepository.save(userMapper.fromRegistrationToUser(userRegistrationRequest));

        UserRepresentation createdUser = userRepresentations.getFirst();
        try {
            sendEmailVerification(createdUser.getId());
        } catch (InternalServerErrorException e){
            log.error("Error sending email verification");
        }
    }


    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        String username = user.getUsername();
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        if (userRepresentations.isEmpty()) {
            throw new CustomerNotFoundException("User not found");
        }
        usersResource.delete(userRepresentations.getFirst().getId());
        userRepository.deleteById(id);
    }

    public void forgotPassword(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new CustomerNotFoundException("User with this username or email not found"));
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(user.getUsername(), true);
        if (userRepresentations.isEmpty()) {
            throw new CustomerNotFoundException("User not found");
        }
        usersResource.get(userRepresentations.getFirst().getId()).executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    public String getKeycloakUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        String username = user.getUsername();
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        if (userRepresentations.isEmpty()) {
            throw new CustomerNotFoundException("User not found in isEmpty");
        }
        return userRepresentations.getFirst().getId();
    }

    public UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

    public void sendEmailVerification(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    public UserResource getUser(String userId) {
        UsersResource usersResource = getUsersResource();
        String keycloakId = getKeycloakUserId(userId);
        return usersResource.get(keycloakId);
    }

    public String getUserId(String keycloakId) {
        UserResource userResource = getUsersResource().get(keycloakId);
        User user = userRepository.findByEmail(userResource.toRepresentation().getEmail()).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        return user.getId();
    }

    @Cacheable("credentials")
    public boolean checkCredentials(String userId, String keycloakId) {
        return getKeycloakUserId(userId).equals(keycloakId);
    }

    public AccessTokenResponse login(LoginRequest loginRequest) {
        Keycloak loginKeycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("password")
                .build();
        AccessTokenResponse accessTokenResponse = loginKeycloak.tokenManager().getAccessToken();
        loginKeycloak.close();
        return accessTokenResponse;

    }

}
