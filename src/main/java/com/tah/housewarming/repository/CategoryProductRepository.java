package com.tah.housewarming.repository;

import com.tah.housewarming.domain.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    List<CategoryProduct> findAllByCategoryId(Integer categoryId);
}
