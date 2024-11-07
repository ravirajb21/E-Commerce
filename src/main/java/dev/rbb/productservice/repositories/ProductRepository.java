package dev.rbb.productservice.repositories;

import dev.rbb.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {

    Product save(Product product);

    Product findProductById(Long id);
}
