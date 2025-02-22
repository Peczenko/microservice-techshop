package project.org.techshop.kafka;

import lombok.Builder;
import lombok.Data;
import project.org.techshop.dto.UserOrderInfoDto;
import project.org.techshop.enitity.PaymentMethod;

@Data
@Builder
public class OrderPaymentRequest {
    private double totalPrice;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private UserOrderInfoDto userOrderInfo;
}
