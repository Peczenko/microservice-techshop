package project.org.techshop.kafka.order;

import lombok.Data;

@Data
public class OrderStatusChange {
    private Long orderId;
    private String status;
    private UserOrderInfoDto userOrderInfoDto;
}
