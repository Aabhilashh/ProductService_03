package dev.abhi.project_03.Services;

import dev.abhi.project_03.Exceptions.ProductNotFoundException;
import dev.abhi.project_03.Models.Category;
import dev.abhi.project_03.Models.Product;
import dev.abhi.project_03.dtos.FakeStoreProductDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public  class FakeStoreProductService implements ProductService{

    private  RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                        FakeStoreProductDto.class
                );
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Any data is not present with this ProductId"
            +productId);
        }

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

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/"+productId,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(response);
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
