package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {
    private String accessToken;
    private int expiresIn;
    private String refreshToken;
    private int refreshExpiresIn;
    private String tokenType;
    private String error;
}
