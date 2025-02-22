package project.org.techshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductsToPurchaseRequest {
    @NotNull(message = "Product id is required")
    private Long productId;
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private int quantity;
}
