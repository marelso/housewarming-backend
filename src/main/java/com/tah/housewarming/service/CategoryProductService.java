package com.tah.housewarming.service;

import com.tah.housewarming.domain.CategoryProduct;
import com.tah.housewarming.repository.CategoryProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryProductService {
    private final CategoryProductRepository repository;
}
