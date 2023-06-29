package com.tah.housewarming.service;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.dto.factory.CategoryFactory;
import com.tah.housewarming.exception.IncorrectValueException;
import com.tah.housewarming.exception.NotFoundException;
import com.tah.housewarming.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryProductService relationService;
    private final CategoryRepository repository;
    private final CategoryFactory factory;

    public List<Category> findAll() {
        return this.repository.findAll();
    }

    public Category findById(Integer id) {
        return get(id).orElseThrow(() -> new NotFoundException("Category" + id));
    }

    public Optional<Category> get(Integer id) {
        return repository.findById(id);
    }

    public Category create(String category) {
        if (categoryAlreadyTaken(category)) throw new IncorrectValueException("This category already exists.");

        return this.repository.save(factory.from(category));
    }

    private boolean categoryAlreadyTaken(String name) {
        var category = repository.findByName(name);

        return category.isPresent();
    }

    public Category update(Integer id, Category category) {
        var existingCategory = findById(id);

        if (categoryAlreadyTaken(category.getName()))
            throw new IncorrectValueException("This category already exists.");

        existingCategory = factory.from(category, existingCategory);

        return repository.save(existingCategory);
    }

    public void delete(int id) {
        var category = findById(id);

        repository.delete(category);
    }

    public List<String> getCategoriesNamesByProduct(Integer productId) {
        var ids = this.relationService.findByProductId(productId);

        return ids.stream().map(id -> findById(id).getName()).collect(Collectors.toList());
    }
}
