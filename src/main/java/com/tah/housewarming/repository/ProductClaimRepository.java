package com.tah.housewarming.repository;

import com.tah.housewarming.domain.ProductClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductClaimRepository extends JpaRepository<ProductClaim, Integer> {

    Integer countByProductIdAndAvailableTrue(Integer productId);

    void deleteAllByProductId(Integer productId);

    Optional<ProductClaim> findFirstByProductIdAndAvailableTrue(Integer productId);
}