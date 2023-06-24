package com.tah.housewarming.repository;

import com.tah.housewarming.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<Category, Integer> {
}
