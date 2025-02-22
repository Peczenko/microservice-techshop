package project.org.techshop.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UserOrderInfo {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
