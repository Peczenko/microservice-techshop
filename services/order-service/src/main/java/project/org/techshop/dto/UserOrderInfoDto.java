package project.org.techshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderInfoDto {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
}
