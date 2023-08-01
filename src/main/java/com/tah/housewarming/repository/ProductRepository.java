package com.tah.housewarming.repository;

import com.tah.housewarming.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findFirstByName(String name);

    Optional<Product> findFirstByBrand(String brand);
}
