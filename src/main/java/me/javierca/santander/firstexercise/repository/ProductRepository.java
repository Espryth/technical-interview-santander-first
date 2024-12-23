package me.javierca.santander.firstexercise.repository;

import me.javierca.santander.firstexercise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Modifying
  @Query("UPDATE Product p SET p.name = :name, p.price = :price, p.description = :description WHERE p.id = :id")
  int update(final Long id, final String name, final Double price, final String description);

}
