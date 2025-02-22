package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;
import project.org.techshop.enitity.OrderStatus;
import project.org.techshop.enitity.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderToResponse {
    private Long id;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAddressDto deliveryAddress;
    private List<ProductToPurchaseResponse> productsToOrder;

}
