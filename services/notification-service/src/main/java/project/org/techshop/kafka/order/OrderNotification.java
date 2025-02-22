package project.org.techshop.kafka.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderNotification {
    private Long orderId;
    private double totalPrice;
    private UserOrderInfoDto UserOrderInfoDto;
    private List<ProductServiceResponse> products;
}
