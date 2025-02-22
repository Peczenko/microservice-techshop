package project.org.techshop.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.org.techshop.kafka.OrderPaymentRequest;
import project.org.techshop.kafka.OrderPaymentResponse;

@FeignClient(
        name = "payment-service",
        url = "${payment-service.url}"
)
public interface PaymentClient {
    @PostMapping("/create")
    OrderPaymentResponse sendPurchaseRequest(@RequestBody OrderPaymentRequest orderPaymentRequest);
}
