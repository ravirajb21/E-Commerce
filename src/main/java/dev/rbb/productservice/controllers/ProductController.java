package dev.rbb.productservice.controllers;

import dev.rbb.productservice.dtos.ErrorResponseDto;
import dev.rbb.productservice.dtos.GetSingleProductResponseDto;
import dev.rbb.productservice.dtos.ProductDto;
import dev.rbb.productservice.exceptions.NotFoundException;
import dev.rbb.productservice.models.Category;
import dev.rbb.productservice.models.Product;
import dev.rbb.productservice.repositories.ProductRepository;
import dev.rbb.productservice.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccessforyoulol");

        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()) {
            throw new NotFoundException("No product exists with ID: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );
        return response;
    }

    // don't take Product as a argument. Take ProductDto
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {

        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());

        newProduct = productRepository.save(newProduct);


//        Product newProduct = productService.addNewProduct(
//                product
//        );
        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }


    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }


    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "deleting a product with ID: " +productId;
    }
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> exceptionMessage (Exception exception) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
}
