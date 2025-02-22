package project.org.techshop.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ProductPurchaseRequest {
    private Long productId;
    private int quantity;
}
