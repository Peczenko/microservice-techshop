package project.org.techshop.kafka.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderInfo {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
