package project.org.techshop.service;

import org.springframework.stereotype.Service;
import project.org.techshop.dto.UserInfoForOrder;
import project.org.techshop.dto.UserRegistrationRequest;
import project.org.techshop.dto.UserRequest;
import project.org.techshop.dto.UserResponse;
import project.org.techshop.entity.User;

@Service
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .build();
    }

    public User toUser(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .address(userRequest.getAddress())
                .build();
    }

    public UserInfoForOrder toUserInfoForOrder(User user) {
        return UserInfoForOrder.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .build();
    }

    public User fromRegistrationToUser(UserRegistrationRequest userRegistrationRequest) {
        return User.builder()
                .username(userRegistrationRequest.getUsername())
                .email(userRegistrationRequest.getEmail())
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .build();
    }
}
