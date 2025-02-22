package project.org.techshop.kafka;

import lombok.Data;

@Data
public class OrderPaymentResponse {
    private boolean success;
    private String paymentIdReference;
}
