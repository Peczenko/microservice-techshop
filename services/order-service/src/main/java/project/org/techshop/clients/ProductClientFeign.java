package project.org.techshop.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.org.techshop.dto.ProductPurchaseRequest;
import project.org.techshop.dto.ProductServiceResponse;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductClientFeign {

    @PostMapping("/purchase")
    Optional<List<ProductServiceResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> products);
}

