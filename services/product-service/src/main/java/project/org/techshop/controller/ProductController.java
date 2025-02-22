package project.org.techshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.org.techshop.dto.ProductRequest;
import project.org.techshop.dto.ProductResponse;
import project.org.techshop.dto.ProductsToPurchaseRequest;
import project.org.techshop.dto.ProductsToPurchaseResponse;
import project.org.techshop.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestPart("product") ProductRequest product,
                                                      @RequestPart("photos") MultipartFile[] photos
    ) throws IOException {
        return ResponseEntity.ok(productService.addProduct(product, photos));
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/photos")
    public ResponseEntity<ProductResponse> addPhotos(@PathVariable Long id,
                                                    @RequestPart("photo") MultipartFile[] photo
    ) throws IOException {
        return ResponseEntity.ok(productService.addPhoto(id, photo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/photos")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        productService.deletePhotoById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@customSecurity.isOrderService(authentication)")
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductsToPurchaseResponse>> purchaseProducts(@RequestBody List<ProductsToPurchaseRequest> products) {
        return ResponseEntity.ok(productService.purchaseProducts(products));
    }

}
