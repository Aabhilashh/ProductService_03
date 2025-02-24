package dev.abhi.project_03.Controllers;

import dev.abhi.project_03.Models.Product;
import dev.abhi.project_03.Services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }
        @GetMapping("/{id}")

    public Product getProductById(@PathVariable("id")Long id){
//            Product product =new Product();
//            product.setId(id);
//            product.setPrice(10.0);
//            product.setTitle("Product Title");
//
//            return product;
            return productService.getSingleProduct(id);
    }

    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

//    public addNewProduct(){
//        return new Product();
//    }
}
