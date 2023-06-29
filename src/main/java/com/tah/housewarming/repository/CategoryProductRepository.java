package com.tah.housewarming.repository;

import com.tah.housewarming.domain.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    List<CategoryProduct> findAllByCategoryId(Integer categoryId);
    List<CategoryProduct> findAllByProductId(Integer productId);
    Optional<CategoryProduct> findAllByCategoryIdAndProductId(Integer categoryId, Integer productId);
    void deleteByCategoryId(Integer categoryId);
    void deleteByProductId(Integer productId);
}
