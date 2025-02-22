package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductToPurchaseResponse {
    private Long productId;
    private double quantityToOrder;
    private double price;
}
