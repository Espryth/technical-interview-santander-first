package me.javierca.santander.firstexercise.service;

import me.javierca.santander.firstexercise.dao.ProductDAO;
import me.javierca.santander.firstexercise.model.Product;
import me.javierca.santander.firstexercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Autowired
  ProductServiceImpl(final ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public ResponseEntity<Product> createProduct(final ProductDAO productDAO) {
    return ResponseEntity.ok(this.repository.save(productDAO.toProduct()));
  }

  @Override
  @Transactional
  public ResponseEntity<Product> updateProduct(final Long id, final ProductDAO productDAO) {
    final var updated = this.repository.update(id, productDAO.name(), productDAO.price(), productDAO.description());
    if (updated == 0) {
      return ResponseEntity.noContent().build();
    }
    return this.findProductById(id);
  }

  @Override
  public ResponseEntity<Product> deleteProduct(final Long id) {
    return this.repository.findById(id)
      .map(product -> {
        this.repository.delete(product);
        return ResponseEntity.ok(product);
      })
      .orElse(ResponseEntity.noContent().build());
  }

  @Override
  public ResponseEntity<Product> findProductById(final Long id) {
    return this.repository.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.noContent().build());
  }

  @Override
  public ResponseEntity<Collection<Product>> findAllProducts() {
    return ResponseEntity.ok(this.repository.findAll());
  }

}
