package project.org.techshop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private CategoryResponse category;
    private List<PhotoResponse> photos;

}
