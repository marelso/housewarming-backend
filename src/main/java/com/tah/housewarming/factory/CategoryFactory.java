package com.tah.housewarming.factory;

import com.tah.housewarming.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

    public Category from(String name) {
        var category = new Category();
        category.setName(name);

        return category;
    }

    public Category from(Category category) {
        return new Category(category.getId(), category.getName());
    }

}
