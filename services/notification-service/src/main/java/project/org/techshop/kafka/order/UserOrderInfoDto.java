package project.org.techshop.kafka.order;

import lombok.Data;

@Data
public class UserOrderInfoDto {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
