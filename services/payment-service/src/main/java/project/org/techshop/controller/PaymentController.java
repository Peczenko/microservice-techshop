package project.org.techshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.org.techshop.dto.PaymentControllerResponse;
import project.org.techshop.dto.PaymentRequest;
import project.org.techshop.dto.PaymentResponse;
import project.org.techshop.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasRole('ADMIN') or @customSecurity.isOrderService(authentication)")
    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<PaymentControllerResponse>> getAllPayments(){
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PreAuthorize("hasRole('ADMIN') or (@customSecurity.isUserOwnerByPaymentId(#id, authentication) and hasRole('MEMBER'))")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentControllerResponse> getPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or (@customSecurity.isUserOwner(#userId, authentication) and hasRole('MEMBER'))")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentControllerResponse>> getPaymentsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
    }
}
