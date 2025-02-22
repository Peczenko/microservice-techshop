package project.org.techshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import project.org.techshop.entity.PaymentMethod;

@Data
public class PaymentRequest {
    @NotNull(message = "Total price is required")
    @Positive(message = "Total price must be positive")
    private double totalPrice;
    @NotNull(message = "Order id is required")
    private Long orderId;
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
    private UserOrderInfo userOrderInfo;
}
