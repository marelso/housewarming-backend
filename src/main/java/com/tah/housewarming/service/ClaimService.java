package com.tah.housewarming.service;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.domain.ProductClaim;
import com.tah.housewarming.exception.IncorrectValueException;
import com.tah.housewarming.repository.ProductClaimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ClaimService {
    private final ProductClaimRepository repository;

    public Integer insert(Product productId, Integer quantity) {
        for (int i = 0; i < quantity; i++) repository.save(from(productId));

        return this.countByProductId(productId.getId());
    }

    public Integer getProductQuantity(Integer productId) {
        return this.countByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<ProductClaim> findByCategoryId(Integer categoryId) {
        return this.repository.findByCategoryId(categoryId);
    }

    private Integer countByProductId(Integer productId) {
        return repository.countByProductIdAndAvailableTrue(productId);
    }

    //TODO this should be in factory layer
    private ProductClaim from(Product productId) {
        var entry = new ProductClaim();

        entry.setProduct(productId);
        entry.setUsername(null);
        entry.setAvailable(true);

        return entry;
    }

    public void deleteByProductId(Integer id) {
        this.repository.deleteAllByProductId(id);
    }

    public void claimProduct(Integer id, String username) {
        var entry = this.repository.findFirstByProductIdAndAvailableTrue(id);

        if(entry.isEmpty())
            throw new IncorrectValueException("This product is no longer available.");

        var product = entry.get();
        product.setAvailable(false);
        product.setUsername(username);

        this.repository.save(product);
    }

    public boolean isAvailable(Integer id) {
        return getProductQuantity(id) > 0;
    }
}
