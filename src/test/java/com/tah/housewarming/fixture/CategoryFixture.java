package com.tah.housewarming.fixture;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.domain.Product;
import com.tah.housewarming.util.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryFixture {
    private Integer id;
    private String name;

    public static CategoryFixture get() {
        return new CategoryFixture();
    }

    public Category build() {
        return new Category(
                id
                , name
        );
    }

    public CategoryFixture random() {
        this.id = RandomGenerator.integer();
        this.name = RandomGenerator.string();

        return this;
    }

    public CategoryFixture withId(Integer id) {
        this.id = id;

        return this;
    }

    public CategoryFixture withName(String name) {
        this.name = name;

        return this;
    }
}
