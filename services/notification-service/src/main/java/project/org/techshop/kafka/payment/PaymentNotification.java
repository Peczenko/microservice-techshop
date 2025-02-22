package project.org.techshop.kafka.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNotification {
    private Long paymentId;
    private Long orderId;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private boolean success;
    private UserOrderInfo userInfo;
}
