package project.org.techshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Description is required")
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private double price;
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private int quantity;
    @NotNull(message = "Category is required")
    private Long categoryId;
}
