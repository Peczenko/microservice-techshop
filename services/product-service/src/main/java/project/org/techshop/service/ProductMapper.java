package project.org.techshop.service;

import org.springframework.stereotype.Service;
import project.org.techshop.dto.*;
import project.org.techshop.entity.Category;
import project.org.techshop.entity.Photo;
import project.org.techshop.entity.Product;

@Service
public class ProductMapper {
    public ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(
                        CategoryResponse.builder()
                                .id(product.getCategory().getId())
                                .name(product.getCategory().getName())
                                .description(product.getCategory().getDescription())
                                .build()
                )
                .photos(
                        product.getPhotos().stream().map(this::mapToPhotoResponse).toList()
                )
                .build();
    }

    public Product mapToEntity(ProductRequest product, Category category) {
        return Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(category)
                .build();
    }

    public PhotoResponse mapToPhotoResponse(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .data(photo.getData())
                .display_order(photo.getDisplay_order())
                .build();
    }
    public ProductsToPurchaseResponse mapToProductsToPurchaseResponse(Product product, int orderedQuantity) {
        return ProductsToPurchaseResponse.builder()
                .productId(product.getId())
                .quantity(orderedQuantity)
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

}
