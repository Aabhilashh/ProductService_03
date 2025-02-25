package dev.abhi.project_03.Controllers;

import dev.abhi.project_03.Models.Product;
import dev.abhi.project_03.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }
  //      @GetMapping("/{id}")

//    public Product getProductById(@PathVariable("id")Long id){
// //           1. trying with manual input
////            Product product =new Product();
////            product.setId(id);
////            product.setPrice(10.0);
////            product.setTitle("Product Title");
////
////            return product;
//
//
//            //return productService.getSingleProduct(id);
//    }

//    //           2. trying responseEntity
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){
//
//            ResponseEntity<Product> responseEntity = new ResponseEntity<>(
//                    productService.getSingleProduct(id),
//                    HttpStatus.OK
//            );
//
//        return responseEntity ;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id")Long id){

        ResponseEntity<Product> responseEntity = null;
        try {
            Product product = productService.getSingleProduct(id);
            responseEntity = new ResponseEntity<>(
                    product,
                    HttpStatus.OK
            );
        }catch(RuntimeException e){
            responseEntity = new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        return responseEntity ;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody(required = true) Product product){
        return productService.updateProduct(id,product);

    }

//    @PutMapping("/{id}")
//    public Product replaceProduct( Long id, Product product){
//            return null;
//    }
}
