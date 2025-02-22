package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentResponse {
    private Long paymentId;
    private boolean success;
}
