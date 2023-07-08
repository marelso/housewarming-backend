package com.tah.housewarming.service;

import com.tah.housewarming.domain.ProductClaim;
import com.tah.housewarming.exception.IncorrectValueException;
import com.tah.housewarming.repository.ProductClaimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClaimService {
    private final ProductClaimRepository repository;

    public Integer insert(Integer productId, Integer quantity) {
        for (int i = 0; i < quantity; i++) repository.save(from(productId));

        return this.countByProductId(productId);
    }

    public Integer getProductQuantity(Integer productId) {
        return this.countByProductId(productId);
    }

    private Integer countByProductId(Integer productId) {
        return repository.countByProductIdAndAvailableTrue(productId);
    }

    //TODO this should be in factory layer
    private ProductClaim from(Integer productId) {
        var entry = new ProductClaim();

        entry.setProductId(productId);
        entry.setUsername(null);
        entry.setAvailable(true);

        return entry;
    }

    public void deleteByProductId(Integer id) {
        this.repository.deleteAllByProductId(id);
    }

    public void claimProduct(Integer id) {
        var entry = this.repository.findFirstByProductIdAndAvailableTrue(id);

        if(entry.isEmpty())
            throw new IncorrectValueException("This product is no longer available.");

        var product = entry.get();
        product.setAvailable(false);

        this.repository.save(product);
    }

    public boolean isAvailable(Integer id) {
        return getProductQuantity(id) > 0;
    }
}
