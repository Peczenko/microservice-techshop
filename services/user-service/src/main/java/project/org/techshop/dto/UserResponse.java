package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;
import project.org.techshop.entity.Address;

@Data
@Builder
public class UserResponse {
        private String id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;
        private Address address;
}
