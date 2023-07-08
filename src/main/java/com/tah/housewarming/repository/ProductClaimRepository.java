package com.tah.housewarming.repository;

import com.tah.housewarming.domain.ProductClaim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductClaimRepository extends JpaRepository<ProductClaim, Integer> {

    Integer countByProductIdAndAvailableTrue(Integer productId);

    void deleteAllByProductId(Integer productId);

    Optional<ProductClaim> findFirstByProductIdAndAvailableTrue(Integer productId);
}