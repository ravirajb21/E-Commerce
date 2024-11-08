package dev.rbb.productservice.services;

import dev.rbb.productservice.dtos.ProductDto;
import dev.rbb.productservice.exceptions.NotFoundException;
import dev.rbb.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    Product updateProduct(Long productId, Product product);
    // if (product.getImageUrl() != null) {
    //
    // }
    Product replaceProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}

// update product with id 123
// {
//   name: iPhone 15
// }