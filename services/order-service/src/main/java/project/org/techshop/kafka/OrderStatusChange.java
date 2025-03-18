package project.org.techshop.kafka;

import lombok.Builder;
import lombok.Data;
import project.org.techshop.dto.UserOrderInfoDto;

@Data
@Builder
public class OrderStatusChange {
    private Long orderId;
    private String status;
    private UserOrderInfoDto userOrderInfoDto;
}
