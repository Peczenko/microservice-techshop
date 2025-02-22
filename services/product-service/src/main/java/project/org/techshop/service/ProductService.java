package project.org.techshop.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.org.techshop.dto.ProductRequest;
import project.org.techshop.dto.ProductResponse;
import project.org.techshop.dto.ProductsToPurchaseRequest;
import project.org.techshop.dto.ProductsToPurchaseResponse;
import project.org.techshop.entity.Category;
import project.org.techshop.entity.Photo;
import project.org.techshop.entity.Product;
import project.org.techshop.exception.ProductServiceException;
import project.org.techshop.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapToResponse).toList();
    }

    public ProductResponse addProduct(ProductRequest product, MultipartFile[] adsImages) throws IOException {
        Category category = productRepository.findCategoryById(product.getCategoryId())
                .orElseThrow(() -> new ProductServiceException("Category not found"));

        Product newProduct = productMapper.mapToEntity(product, category);

        List<Photo> photoList = new ArrayList<>();
        for(MultipartFile file : adsImages) {
            Photo photo = new Photo();
            photo.setFileName(file.getOriginalFilename());
            photo.setData(file.getBytes());

            photo.setProduct(newProduct);
            photoList.add(photo);
        }

        newProduct.setPhotos(photoList);

        Product savedProduct = productRepository.save(newProduct);
        return productMapper.mapToResponse(savedProduct);
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.mapToResponse(product);
    }

    public ProductResponse addPhoto(Long id, MultipartFile[] adsImages) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        log.info("Photo size: {}", adsImages.length);
        log.info("Photo: {}", adsImages[0].toString());
        List<Photo> photoList = new ArrayList<>();
        for(MultipartFile file : adsImages) {
            Photo photo = new Photo();
            photo.setFileName(file.getOriginalFilename());
            photo.setData(file.getBytes());

            photo.setProduct(product);
            product.getPhotos().add(photo);
        }


        Product savedProduct = productRepository.save(product);
        return productMapper.mapToResponse(savedProduct);
    }

    public void deletePhotoById(Long id) {
        productRepository.deletePhotoById(id);
    }

    public List<ProductsToPurchaseResponse> purchaseProducts(List<ProductsToPurchaseRequest> products) {
        List<ProductsToPurchaseResponse> purchasedProducts = new ArrayList<>();

        for(ProductsToPurchaseRequest product : products) {
            Product productEntity = productRepository.findById(product.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if(productEntity.getQuantity() < product.getQuantity()) {
                throw new RuntimeException("Not enough quantity");
            }

            productEntity.setQuantity(productEntity.getQuantity() - product.getQuantity());
            Product savedProduct = productRepository.save(productEntity);
            int orderedQuantity = product.getQuantity();
            purchasedProducts.add(productMapper.mapToProductsToPurchaseResponse(savedProduct, orderedQuantity));
        }

        return purchasedProducts;
    }

}
