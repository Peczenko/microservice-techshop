package project.org.techshop.kafka;

import lombok.*;
import project.org.techshop.dto.ProductServiceResponse;
import project.org.techshop.dto.UserOrderInfoDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderNotification {
    private Long orderId;
    private double totalPrice;
    private UserOrderInfoDto userOrderInfoDto;
    private List<ProductServiceResponse> products;
}
