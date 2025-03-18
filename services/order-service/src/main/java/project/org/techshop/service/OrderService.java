package project.org.techshop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import project.org.techshop.clients.PaymentClient;
import project.org.techshop.clients.ProductClientFeign;
import project.org.techshop.clients.UserFeignClient;
import project.org.techshop.dto.*;
import project.org.techshop.enitity.Address;
import project.org.techshop.enitity.Order;
import project.org.techshop.enitity.OrderStatus;
import project.org.techshop.exception.OrderNotFoundException;
import project.org.techshop.kafka.*;
import project.org.techshop.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClientFeign productClient;
    private final UserFeignClient userInfoClient;
    private final KafkaProducer kafkaProducer;
    private final PaymentClient paymentClient;
    private final OrderStatusUpdater orderStatusUpdater;

    public List<OrderToResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::mapToOrderToResponse).toList();
    }

    public List<OrderToResponse> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findAllByCustomerId(userId);
        return orders.stream().map(orderMapper::mapToOrderToResponse).toList();
    }

    public OrderToResponse createOrder(OrderToPlace orderToPlace) {
        UserOrderInfo userOrderInfo = userInfoClient.getUserInfoForOrder(orderToPlace.getUserId()).orElseThrow(() -> new RuntimeException("Error while getting user info"));

        List<ProductServiceResponse> productsToPurchase = productClient.purchaseProducts(orderToPlace.getProductsToOrder()).orElseThrow(() -> new RuntimeException("Error while purchasing products"));


        Order order = Order.builder()
                .customerId(orderToPlace.getUserId())
                .deliveryAddress(
                        Address.builder()
                                .country(userOrderInfo.getAddress().getCountry())
                                .state(userOrderInfo.getAddress().getState())
                                .city(userOrderInfo.getAddress().getCity())
                                .street(userOrderInfo.getAddress().getStreet())
                                .zipCode(userOrderInfo.getAddress().getZipCode())
                                .houseNumber(userOrderInfo.getAddress().getHouseNumber())
                                .apartmentNumber(userOrderInfo.getAddress().getApartmentNumber())
                                .build()
                )
                .productsToOrder(
                        productsToPurchase.stream().map(orderMapper::mapToProductToOrder).collect(Collectors.toList())
                )
                .orderStatus(OrderStatus.NEW)
                .paymentMethod(orderToPlace.getPaymentMethod())
                .build();
        Order savedOrder = orderRepository.save(order);

        double totalPrice = productsToPurchase.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;




        kafkaProducer.sendOrderNotification(
                OrderNotification.builder()
                        .orderId(savedOrder.getId())
                        .totalPrice(totalPrice)
                        .products(productsToPurchase)
                        .userOrderInfoDto(
                                UserOrderInfoDto.builder()
                                        .userId(userOrderInfo.getId())
                                        .email(userOrderInfo.getEmail())
                                        .firstName(userOrderInfo.getFirstName())
                                        .lastName(userOrderInfo.getLastName())
                                        .build()
                        )
                        .build()
        );



        OrderPaymentResponse paymentResponse =  paymentClient.sendPurchaseRequest(
                OrderPaymentRequest.builder()
                        .orderId(savedOrder.getId())
                        .paymentMethod(orderToPlace.getPaymentMethod())
                        .totalPrice(totalPrice)
                        .userOrderInfo(
                                UserOrderInfoDto.builder()
                                        .userId(userOrderInfo.getId())
                                        .email(userOrderInfo.getEmail())
                                        .firstName(userOrderInfo.getFirstName())
                                        .lastName(userOrderInfo.getLastName())
                                        .build()
                        )
                        .build()
        );

        log.info("Order status: {}", paymentResponse);
        if(paymentResponse.isSuccess()){
            savedOrder.setOrderStatus(OrderStatus.PENDING);
            log.info("Order {} has been payed successfully", savedOrder.getId());
        } else {
            savedOrder.setOrderStatus(OrderStatus.CANCELLED);
            log.info("Order  {} has been cancelled", savedOrder);
        }
        Order updatedOrder = orderRepository.save(savedOrder);

        if(updatedOrder.getOrderStatus().equals(OrderStatus.PENDING)) {
            orderStatusUpdater.startStatusUpdate(updatedOrder);
        }

        return orderMapper.mapToOrderToResponse(updatedOrder);
    }


    @EventListener
    public void handleOrderStatusChangedEvent(OrderStatusChangedEvent event) {
        Order order = event.getOrder();
        sendOrderStatusChangeNotification(order);
    }

    public void sendOrderStatusChangeNotification(Order order) {
        UserOrderInfo userOrderInfo = userInfoClient.getUserInfoForOrder(order.getCustomerId()).orElseThrow(() -> new RuntimeException("Error while getting user info"));

        OrderStatusChange orderStatusChange = OrderStatusChange.builder()
                .orderId(order.getId())
                .status(order.getOrderStatus().name())
                .userOrderInfoDto(
                        UserOrderInfoDto.builder()
                                .userId(userOrderInfo.getId())
                                .email(userOrderInfo.getEmail())
                                .firstName(userOrderInfo.getFirstName())
                                .lastName(userOrderInfo.getLastName())
                                .build()
                )
                .build();

        kafkaProducer.sendOrderStatusChangeNotification(orderStatusChange);
    }

    public OrderToResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
        return orderMapper.mapToOrderToResponse(order);
    }

    public Order getOrderEntityById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id: " + id + " not found"));
    }
}
