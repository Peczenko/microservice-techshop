package project.org.techshop.kafka;

import lombok.Builder;
import lombok.Data;
import project.org.techshop.dto.UserOrderInfo;
import project.org.techshop.entity.PaymentMethod;

@Data
@Builder
public class PaymentNotification {
    private Long paymentId;
    private Long orderId;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private boolean success;
    private UserOrderInfo userInfo;

}
