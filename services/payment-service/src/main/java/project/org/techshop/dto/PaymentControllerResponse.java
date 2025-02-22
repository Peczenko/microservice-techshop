package project.org.techshop.dto;


import lombok.Builder;
import lombok.Data;
import project.org.techshop.entity.PaymentMethod;
import project.org.techshop.entity.PaymentStatus;
import project.org.techshop.entity.UserInfo;

@Data
@Builder
public class PaymentControllerResponse {
    private Long id;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private Long orderId;
    private PaymentStatus paymentStatus;
    private UserInfo userInfo;
}

