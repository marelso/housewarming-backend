package com.tah.housewarming.repository;

import com.tah.housewarming.domain.ProductClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductClaimRepository extends JpaRepository<ProductClaim, Integer> {
}
