package project.org.techshop.service;

import org.springframework.stereotype.Service;
import project.org.techshop.dto.PaymentControllerResponse;
import project.org.techshop.dto.PaymentRequest;
import project.org.techshop.dto.PaymentResponse;
import project.org.techshop.entity.Payment;
import project.org.techshop.entity.UserInfo;

@Service
public class PaymentMapper {
    public Payment mapToEntity(PaymentRequest paymentRequest) {
        return Payment.builder()
                .totalPrice(paymentRequest.getTotalPrice())
                .orderId(paymentRequest.getOrderId())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .userInfo(
                        UserInfo.builder()
                                .email(paymentRequest.getUserOrderInfo().getEmail())
                                .firstName(paymentRequest.getUserOrderInfo().getFirstName())
                                .lastName(paymentRequest.getUserOrderInfo().getLastName())
                                .userId(paymentRequest.getUserOrderInfo().getUserId())
                                .build()
                )
                .build();
    }

    public PaymentResponse mapToResponse(Payment payment, boolean success) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .success(success)
                .build();
    }
    public PaymentControllerResponse mapToControllerResponse(Payment payment) {
        return PaymentControllerResponse.builder()
                .id(payment.getId())
                .totalPrice(payment.getTotalPrice())
                .paymentMethod(payment.getPaymentMethod())
                .orderId(payment.getOrderId())
                .paymentStatus(payment.getPaymentStatus())
                .userInfo(payment.getUserInfo())
                .build();
    }
}
