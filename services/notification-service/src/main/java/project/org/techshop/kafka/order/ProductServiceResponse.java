package project.org.techshop.kafka.order;

import lombok.Data;

@Data
public class ProductServiceResponse {
    private Long productId;
    private int quantity;
    private double price;
    private String name;
    private String description;
}
