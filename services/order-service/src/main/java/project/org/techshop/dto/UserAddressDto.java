package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressDto {
    private String country;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    private String houseNumber;
    private String apartmentNumber;
}
