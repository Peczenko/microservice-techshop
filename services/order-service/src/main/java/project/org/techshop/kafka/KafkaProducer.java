package project.org.techshop.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, OrderNotification> kafkaTemplate;

    public void sendOrderNotification(OrderNotification orderNotification) {
        log.info("Sending order notification to kafka topic: {}", orderNotification);
        Message<OrderNotification> message = MessageBuilder
                .withPayload(orderNotification)
                .setHeader(KafkaHeaders.TOPIC, "order-notification")
                .build();
        kafkaTemplate.send(message);
    }
}
