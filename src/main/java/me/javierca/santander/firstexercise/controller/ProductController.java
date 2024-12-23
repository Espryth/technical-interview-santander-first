package me.javierca.santander.firstexercise.controller;

import jakarta.validation.Valid;
import me.javierca.santander.firstexercise.dao.ProductDAO;
import me.javierca.santander.firstexercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public final class ProductController {

  private final ProductService service;

  @Autowired
  ProductController(final ProductService service) {
    this.service = service;
  }

  @PostMapping()
  ResponseEntity<?> createProduct(final @Valid @RequestBody ProductDAO product) {
    return this.service.createProduct(product);
  }

  @PutMapping("/{id}")
  ResponseEntity<?> updateProduct(final @PathVariable Long id, final @Valid @RequestBody ProductDAO product) {
    return this.service.updateProduct(id, product);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<?> deleteProduct(final @PathVariable Long id) {
    return this.service.deleteProduct(id);
  }

  @GetMapping()
  ResponseEntity<?> getProducts() {
    return this.service.findAllProducts();
  }

  @GetMapping("/{id}")
  ResponseEntity<?> getProduct(final @PathVariable Long id) {
    return this.service.findProductById(id);
  }

}
