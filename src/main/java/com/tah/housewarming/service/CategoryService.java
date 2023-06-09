package com.tah.housewarming.service;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll() {
        return this.repository.findAll();
    }

    public Category findById(Integer id) {
        return get(id)
                .orElseThrow(() -> new RuntimeException("There is no category with id: " + id));
    }

    public Optional<Category> get(Integer id) {
        return repository.findById(id);
    }

    public Category create(Category category) {
        if(categoryAlreadyTaken(category.getId(), category.getName()))
            throw new RuntimeException("This category already exists.");

        return this.repository.save(category);
    }

    private boolean categoryAlreadyTaken(Integer id, String name) {
        var containsId = repository.findById(id);
        var containsName = repository.findByName(name);

        return containsId.isPresent() || containsName.isPresent();
    }
}
