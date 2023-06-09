package com.tah.housewarming.service;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll() {
        return this.repository.findAll();
    }
}
