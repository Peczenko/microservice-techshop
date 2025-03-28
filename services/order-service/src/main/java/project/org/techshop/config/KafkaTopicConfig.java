package project.org.techshop.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderNotificationTopic() {
        return TopicBuilder
                .name("order-notification")
                .build();
    }

    @Bean
    public NewTopic paymentNotificationTopic() {
        return TopicBuilder
                .name("order-status-change-notification")
                .build();
    }
}
