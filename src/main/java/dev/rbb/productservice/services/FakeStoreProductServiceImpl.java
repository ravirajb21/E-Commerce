package dev.rbb.productservice.services;

import dev.rbb.productservice.clients.fakestoreapi.FakeStoreClient;
import dev.rbb.productservice.clients.fakestoreapi.FakeStoreProductDto;
import dev.rbb.productservice.dtos.ProductDto;
import dev.rbb.productservice.models.Category;
import dev.rbb.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class FakeStoreProductServiceImpl implements ProductService {

    final private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        //in Product, Category is category object
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto productDto: fakeStoreProductDtos) {
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        //creating object
        RestTemplate restTemplate = restTemplateBuilder.build();
        //(url, return type, parameters_in_url...)
        //Converting json into product dto ProductDto.class,
        //id = productId
        //ResponseEntity obj contains response
        //why we didn't use only ProductDto because get call can return a error
         ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId);
         //from response entity get a product dto
         FakeStoreProductDto productDto = response.getBody();
        //convert product dto to product..because we are returning a product

        if(productDto == null) {
            return Optional.empty();
        }

         return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }


    @Override
    public Product addNewProduct(ProductDto product) {
        // to add a new product I'll have to send a request. So create an instance of rest template
        RestTemplate restTemplate = restTemplateBuilder.build();
        //url : url to which we are sending post request.
        // product: is our product which we want to add
        //ProductDto.class: response we have to convert into out ProductDto class
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
        "https://fakestoreapi.com/products",
            product,
            FakeStoreProductDto.class
        );
        //from response entity get a product dto
        FakeStoreProductDto productDto = response.getBody();
        //convert product dto to product..because we are returning a product
        return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
