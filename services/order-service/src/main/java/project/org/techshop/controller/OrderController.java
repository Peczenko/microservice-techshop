package project.org.techshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.org.techshop.dto.OrderToPlace;
import project.org.techshop.dto.OrderToResponse;
import project.org.techshop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderToResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isUserOwnerByOrderId(#orderId, authentication)")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderToResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isUserOwner(#orderToPlace.userId, authentication)")
    @PostMapping("/create")
    public ResponseEntity<OrderToResponse> createOrder(@RequestBody OrderToPlace orderToPlace) {
        return ResponseEntity.ok(orderService.createOrder(orderToPlace));
    }

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isUserOwner(#userId, authentication)")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderToResponse>> getOrdersByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
