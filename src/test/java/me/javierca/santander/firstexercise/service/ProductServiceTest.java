package me.javierca.santander.firstexercise.service;

import me.javierca.santander.firstexercise.dao.ProductDAO;
import me.javierca.santander.firstexercise.model.Product;
import me.javierca.santander.firstexercise.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {

  private static final Product PRODUCT = new Product(
      1L,
      "Product 1",
      "Description",
      10.0,
      new Date()
  );

  private static final ProductDAO PRODUCT_DAO = new ProductDAO(
      "Product 1",
      "Description",
      10.0
  );

  @Mock
  private ProductRepository repository;

  @InjectMocks
  private ProductServiceImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateProduct() {
    when(this.repository.save(any())).thenReturn(PRODUCT);
    assertTrue(this.service.createProduct(PRODUCT_DAO).getStatusCode().is2xxSuccessful());
  }

  @Test
  void testUpdateProduct() {
    when(this.repository.update(any(), any(), any(), any())).thenReturn(1);
    when(this.repository.findById(any())).thenReturn(Optional.of(PRODUCT));
    assertTrue(this.service.updateProduct(1L, PRODUCT_DAO).getStatusCode().is2xxSuccessful());
  }

  @Test
  void testDeleteProduct() {
    when(this.repository.findById(any())).thenReturn(Optional.of(PRODUCT));
    assertTrue(this.service.deleteProduct(1L).getStatusCode().is2xxSuccessful());
  }

  @Test
  void testFindProductById() {
    when(this.repository.findById(any())).thenReturn(Optional.of(PRODUCT));
    assertTrue(this.service.findProductById(1L).getStatusCode().is2xxSuccessful());
  }

  @Test
  void testFindAllProducts() {
    when(this.repository.findAll()).thenReturn(List.of(PRODUCT));
    assertTrue(this.service.findAllProducts().getStatusCode().is2xxSuccessful());
  }
}
