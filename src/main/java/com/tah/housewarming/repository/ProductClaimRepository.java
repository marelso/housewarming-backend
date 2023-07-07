package com.tah.housewarming.repository;

import com.tah.housewarming.domain.ProductClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductClaimRepository extends JpaRepository<ProductClaim, Integer> {

    Integer countByProductIdAndAvailableTrue(Integer productId);

}