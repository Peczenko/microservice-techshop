package project.org.techshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.org.techshop.enitity.PaymentMethod;


import java.util.List;

@Data
public class OrderToPlace {
    @NotNull(message = "userId cannot be null")
    @NotBlank(message = "userId cannot be blank")
    private String userId;
    @NotNull(message = "paymentMethod cannot be null")
    private PaymentMethod paymentMethod;
    @NotNull(message = "productsToOrder cannot be null")
    private List<ProductPurchaseRequest> productsToOrder;
}
