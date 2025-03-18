package project.org.techshop.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import project.org.techshop.email.EmailService;
import project.org.techshop.kafka.order.OrderNotification;
import project.org.techshop.kafka.order.OrderStatusChange;
import project.org.techshop.kafka.payment.PaymentNotification;
import project.org.techshop.notification.Notification;
import project.org.techshop.notification.NotificationRepository;
import project.org.techshop.notification.NotificationType;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-notification")
    public void consumeOrderNotification(OrderNotification orderNotification) throws MessagingException {
        log.info("Consumed order notification: {}", orderNotification);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .orderConfirmation(orderNotification)
                        .build()
        );

        String customerName = orderNotification.getUserOrderInfoDto().getFirstName() + " " + orderNotification.getUserOrderInfoDto().getLastName();
        emailService.sendOrderConfirmation(
                orderNotification.getUserOrderInfoDto().getEmail(),
                customerName,
                orderNotification.getTotalPrice(),
                orderNotification.getOrderId(),
                orderNotification.getProducts()
        );

    }

    @KafkaListener(topics = "payment-notification")
    public void consumePaymentNotification(PaymentNotification paymentNotification) throws MessagingException {
        log.info("Consumed payment notification: {}", paymentNotification);

        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .paymentNotification(paymentNotification)
                        .build()

        );

        String customerName = paymentNotification.getUserInfo().getFirstName() + " " + paymentNotification.getUserInfo().getLastName();
        emailService.sendPaymentConfirmation(
                paymentNotification.getUserInfo().getEmail(),
                customerName,
                paymentNotification.getTotalPrice(),
                paymentNotification.getOrderId(),
                paymentNotification.getPaymentMethod(),
                paymentNotification.isSuccess()
        );
    }

    @KafkaListener(topics = "order-status-change-notification")
    public void consumeOrderStatusNotification(OrderStatusChange orderStatusChange) throws MessagingException {
        log.info("Consumed order status change notification: {}", orderStatusChange);

        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_STATUS_CHANGE)
                        .notificationTime(LocalDateTime.now())
                        .orderStatusChange(orderStatusChange)
                        .build()
        );

        String customerName = orderStatusChange.getUserOrderInfoDto().getFirstName() + " " + orderStatusChange.getUserOrderInfoDto().getLastName();
        emailService.sendOrderStatusChangeNotification(
                orderStatusChange.getUserOrderInfoDto().getEmail(),
                customerName,
                orderStatusChange.getOrderId(),
                orderStatusChange.getStatus()
        );
    }

}
