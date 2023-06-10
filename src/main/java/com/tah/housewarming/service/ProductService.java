package com.tah.housewarming.service;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product findById(Integer id) {
        return get(id)
                .orElseThrow(() -> new RuntimeException("There is no product with id: " + id));
    }

    private Optional<Product> get(Integer id) {
        return this.repository.findById(id);
    }
}
