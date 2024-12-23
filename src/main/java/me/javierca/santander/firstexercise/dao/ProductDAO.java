package me.javierca.santander.firstexercise.dao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import me.javierca.santander.firstexercise.model.Product;

public record ProductDAO(
    @NotNull String name,
    @NotNull String description,
    @Positive @NotNull Double price
) {

  public Product toProduct() {
    return Product.builder()
        .name(this.name())
        .description(this.description())
        .price(this.price())
        .build();
  }
}
