package project.org.techshop.dto;

import lombok.Data;

@Data
public class UserOrderInfo {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private UserAddressDto address;
}
