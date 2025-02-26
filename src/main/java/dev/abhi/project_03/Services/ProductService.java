package dev.abhi.project_03.Services;

import dev.abhi.project_03.Exceptions.ProductNotFoundException;
import dev.abhi.project_03.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);


}
