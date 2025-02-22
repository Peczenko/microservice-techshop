package project.org.techshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.org.techshop.entity.Address;

@Data
public class UserRequest {
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "email is required")
    @Email(message = "email should be valid")
    private String email;
    @NotNull(message = "firstName is required")
    private String firstName;
    @NotNull(message = "lastName is required")
    private String lastName;
    private Address address;
}
