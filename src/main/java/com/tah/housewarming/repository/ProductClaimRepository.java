package com.tah.housewarming.repository;

import com.tah.housewarming.domain.ProductClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductClaimRepository extends JpaRepository<ProductClaim, Integer> {

    @Query(value = "SELECT pc.* FROM product_claim pc " +
            "INNER JOIN product p ON pc.product_id = p.id " +
            "INNER JOIN category_product cp ON p.id = cp.product_id " +
            "WHERE cp.category_id = :categoryId AND pc.available = true", nativeQuery = true)
    List<ProductClaim> findByCategoryId(@Param("categoryId") Integer categoryId);

    Integer countByProductIdAndAvailableTrue(Integer productId);

    void deleteAllByProductId(Integer productId);

    Optional<ProductClaim> findFirstByProductIdAndAvailableTrue(Integer productId);
}