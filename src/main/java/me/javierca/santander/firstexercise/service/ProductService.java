package me.javierca.santander.firstexercise.service;

import me.javierca.santander.firstexercise.dao.ProductDAO;
import me.javierca.santander.firstexercise.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface ProductService {

  ResponseEntity<Product> createProduct(final ProductDAO productDAO);

  ResponseEntity<Product> updateProduct(final Long id, final ProductDAO productDAO);

  ResponseEntity<Product> deleteProduct(final Long id);

  ResponseEntity<Product> findProductById(final Long id);

  ResponseEntity<Collection<Product>> findAllProducts();
}
