package project.org.techshop.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.org.techshop.dto.PaymentControllerResponse;
import project.org.techshop.dto.PaymentRequest;
import project.org.techshop.dto.PaymentResponse;
import project.org.techshop.entity.Payment;
import project.org.techshop.entity.PaymentStatus;
import project.org.techshop.exception.PaymentNotFoundException;
import project.org.techshop.kafka.KafkaProducer;
import project.org.techshop.kafka.PaymentNotification;
import project.org.techshop.repository.PaymentRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final KafkaProducer kafkaProducer;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.mapToEntity(paymentRequest);

        Random r = new Random();
        boolean success = (r.nextDouble() >= 0.2);
        if (success) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            log.info("Payment for order with id: {} was successful", paymentRequest.getOrderId());
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
            log.info("Payment for order with id: {} failed", paymentRequest.getOrderId());
        }
        Payment savedPayment = paymentRepository.save(payment);

        log.info("ORDER IS PAYED: {}", success);

        kafkaProducer.sendNotification(
                PaymentNotification.builder()
                        .orderId(paymentRequest.getOrderId())
                        .paymentId(savedPayment.getId())
                        .totalPrice(paymentRequest.getTotalPrice())
                        .paymentMethod(paymentRequest.getPaymentMethod())
                        .success(success)
                        .userInfo(paymentRequest.getUserOrderInfo())
                        .build()
        );

        return paymentMapper.mapToResponse(savedPayment, success);

    }

    public List<PaymentControllerResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(paymentMapper::mapToControllerResponse).toList();
    }

    public PaymentControllerResponse getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::mapToControllerResponse)
                .orElseThrow(() -> new IllegalArgumentException("Payment with id: " + id + " not found"));
    }


    public List<PaymentControllerResponse> getPaymentsByUserId(Long userId) {
        return paymentRepository.findAllByUserInfo_UserId(userId.toString()).stream().map(paymentMapper::mapToControllerResponse).toList();
    }

    public String getUserIdByPaymentId(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(payment -> payment.getUserInfo().getUserId())
                .orElseThrow(() -> new PaymentNotFoundException("Payment with id: " + paymentId + " not found"));
    }
}
