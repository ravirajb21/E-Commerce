package dev.rbb.productservice.clients.fakestoreapi;

import dev.rbb.productservice.dtos.ProductDto;
import dev.rbb.productservice.exceptions.NotFoundException;
import dev.rbb.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@component becuse we want to let spring know about this class
@Component
public class FakeStoreClient {

    //we will need a rest template for this
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //by default, rest template tries to convert whatever object it gets into hashmap..hence we use arrays
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return Arrays.asList(l.getBody());
    }

    Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException {
        return null;
    }

    FakeStoreProductDto addNewProduct(ProductDto product) {
        return null;
    }

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    FakeStoreProductDto updateProduct(Long productId, Product product) {
        return null;
    }
    // if (product.getImageUrl() != null) {
    //
    // }
    FakeStoreProductDto replaceProduct(Long productId, Product product) {
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId) {
        return null;
    }

}
