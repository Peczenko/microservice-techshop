package project.org.techshop.service;

import org.springframework.stereotype.Service;
import project.org.techshop.dto.OrderToResponse;
import project.org.techshop.dto.ProductServiceResponse;
import project.org.techshop.dto.ProductToPurchaseResponse;
import project.org.techshop.dto.UserAddressDto;
import project.org.techshop.enitity.Order;
import project.org.techshop.enitity.ProductToOrder;

@Service
public class OrderMapper {
    public OrderToResponse mapToOrderToResponse(Order order) {
        return OrderToResponse.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .paymentMethod(order.getPaymentMethod())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .deliveryAddress(
                        UserAddressDto.builder()
                                .country(order.getDeliveryAddress().getCountry())
                                .state(order.getDeliveryAddress().getState())
                                .city(order.getDeliveryAddress().getCity())
                                .street(order.getDeliveryAddress().getStreet())
                                .zipCode(order.getDeliveryAddress().getZipCode())
                                .houseNumber(order.getDeliveryAddress().getHouseNumber())
                                .apartmentNumber(order.getDeliveryAddress().getApartmentNumber())
                                .build()
                )
                .productsToOrder(
                        order.getProductsToOrder().stream().map(this::mapToProductToPurchaseResponse).toList()
                )
                .build();
    }

    public ProductToPurchaseResponse mapToProductToPurchaseResponse(ProductToOrder productToOrder) {
        return ProductToPurchaseResponse.builder()
                .productId(productToOrder.getProductId())
                .quantityToOrder(productToOrder.getQuantity())
                .price(productToOrder.getPrice())
                .build();
    }
    public ProductToOrder mapToProductToOrder(ProductServiceResponse productServiceResponse) {
        return ProductToOrder.builder()
                .productId(productServiceResponse.getProductId())
                .quantity(productServiceResponse.getQuantity())
                .price(productServiceResponse.getPrice())
                .build();
    }

}
