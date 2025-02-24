package dev.abhi.project_03.Services;

import dev.abhi.project_03.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
}
