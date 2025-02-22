package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsToPurchaseResponse {
    private Long productId;
    private int quantity;
    private double price;
    private String name;
    private String description;
}
