package me.javierca.santander.firstexercise.repository;

import jakarta.persistence.EntityManager;
import me.javierca.santander.firstexercise.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Testcontainers
public class ProductRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:latest")
      .withDatabaseName("test")
      .withUsername("test")
      .withPassword("test");

  @DynamicPropertySource
  static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", CONTAINER::getUsername);
    registry.add("spring.datasource.password", CONTAINER::getPassword);
  }

  @Autowired
  private ProductRepository repository;

  @Test
  void testSave() {
    final var product = Product.builder()
        .name("Product 1")
        .price(10.0)
        .description("Description 1")
        .build();
    final var savedProduct = this.repository.save(product);
    assertEquals(product.getName(), savedProduct.getName());
  }

  @Test
  @Transactional
  void testUpdate() {
    final var product = Product.builder()
        .name("Product 1")
        .price(10.0)
        .description("Description 1")
        .build();
    final var savedProduct = this.repository.save(product);
    final var updated = this.repository.update(savedProduct.getId(), "Product 2", 20.0, "Description 2");
    assertEquals(1, updated);
  }

  @Test
  void testDelete() {
    final var product = Product.builder()
        .name("Product 1")
        .price(10.0)
        .description("Description 1")
        .build();
    final var savedProduct = this.repository.save(product);
    this.repository.delete(savedProduct);
    assertNull(this.repository.findById(savedProduct.getId()).orElse(null));
  }

  @Test
  void testFindById() {
    final var product = Product.builder()
        .name("Product 1")
        .price(10.0)
        .description("Description 1")
        .build();
    final var savedProduct = this.repository.save(product);
    assertEquals(product.getName(), this.repository.findById(savedProduct.getId()).orElseThrow().getName());
  }

}
