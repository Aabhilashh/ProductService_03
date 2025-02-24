package dev.abhi.project_03.Services;

import dev.abhi.project_03.Models.Category;
import dev.abhi.project_03.Models.Product;
import dev.abhi.project_03.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private  RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                        FakeStoreProductDto.class
                );

        return  convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
       FakeStoreProductDto[] fakeStoreProductDtos=
               restTemplate.getForObject("https://fakestoreapi.com/products",
                       FakeStoreProductDto[].class);
       List<Product> products=new ArrayList<>();
       for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
           products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
       }
        return products;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
