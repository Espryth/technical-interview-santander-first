package me.javierca.santander.firstexercise.controller;

import me.javierca.santander.firstexercise.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  private static final String PRODUCT = "{\"name\": \"Product 1\", \"price\": 10.0, \"description\": \"Description\"}";

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ProductServiceImpl productService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateProduct() throws Exception {
    when(this.productService.createProduct(any())).thenReturn(ResponseEntity.ok().build());
    this.mockMvc
        .perform(
            post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT)
        )
        .andExpect(status().isOk());
  }

  @Test
  void testUpdateProduct() throws Exception {
    when(this.productService.updateProduct(any(), any())).thenReturn(ResponseEntity.ok().build());
    this.mockMvc
        .perform(
            put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT)
        )
        .andExpect(status().isOk());
  }

  @Test
  void testDeleteProduct() throws Exception {
    when(this.productService.deleteProduct(any())).thenReturn(ResponseEntity.ok().build());
    this.mockMvc
        .perform(
            delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(PRODUCT)
        )
        .andExpect(status().isOk());
  }

  @Test
  void testGetProducts() throws Exception {
    when(this.productService.findAllProducts()).thenReturn(ResponseEntity.ok().build());
    this.mockMvc
        .perform(
            get("/products")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
  }

  @Test
  void testGetProduct() throws Exception {
    when(this.productService.findProductById(any())).thenReturn(ResponseEntity.ok().build());
    this.mockMvc
        .perform(
            get("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
  }
}
