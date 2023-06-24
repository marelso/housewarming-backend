package com.tah.housewarming.service;

import com.tah.housewarming.domain.CategoryProduct;
import com.tah.housewarming.repository.CategoryProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryProductService {
    private final CategoryProductRepository repository;

    public void create(Integer categoryId, Integer productId) {
        if(!relationAlreadyExist(categoryId, productId)) {
            var relation = new CategoryProduct();
            relation.setCategoryId(categoryId);
            relation.setProductId(productId);

            repository.save(relation);
        }
    }
    private Boolean relationAlreadyExist(Integer categoryId, Integer productId) {
        return repository.findAllByCategoryIdAndProductId(categoryId, productId).isPresent();
    }
    public List<CategoryProduct> find(Integer categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }
}
