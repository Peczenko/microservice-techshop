package project.org.techshop.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class Address {
    private String country;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    private String houseNumber;
    private String apartmentNumber;
}
