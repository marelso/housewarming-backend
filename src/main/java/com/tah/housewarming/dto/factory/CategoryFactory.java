package com.tah.housewarming.dto.factory;

import com.tah.housewarming.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

    public Category from(String name) {
        var category = new Category();
        category.setName(name);

        return category;
    }

    public Category from(Category category, Category existing) {
        return new Category(existing.getId(), category.getName());
    }

}
