package com.tah.housewarming.repository;

import com.tah.housewarming.domain.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
}
