package com.tah.housewarming.service;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.factory.CategoryFactory;
import com.tah.housewarming.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryFactory factory;

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

    public Category create(String category) {
        if(categoryAlreadyTaken(category))
            throw new RuntimeException("This category already exists.");

        return this.repository.save(factory.from(category));
    }

    private boolean categoryAlreadyTaken(String name) {
        var containsName = repository.findByName(name);

        return containsName.isPresent();
    }

    public Category update(Category category) {
        var existingCategory = findById(category.getId());

        existingCategory = factory.from(category, existingCategory);

        return repository.save(existingCategory);
    }

    public void delete(int id) {
        var category = findById(id);

        repository.delete(category);
    }
}
