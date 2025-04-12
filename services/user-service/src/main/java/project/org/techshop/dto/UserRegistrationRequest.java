package project.org.techshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.org.techshop.entity.Address;

@Data
public class UserRegistrationRequest {
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Address cannot be null")
    private Address address;
}
