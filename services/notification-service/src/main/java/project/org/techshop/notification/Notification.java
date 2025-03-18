package project.org.techshop.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import project.org.techshop.kafka.order.OrderNotification;
import project.org.techshop.kafka.order.OrderStatusChange;
import project.org.techshop.kafka.payment.PaymentNotification;

import java.time.LocalDateTime;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationTime;
    private OrderNotification orderConfirmation;
    private PaymentNotification paymentNotification;
    private OrderStatusChange orderStatusChange;
}
