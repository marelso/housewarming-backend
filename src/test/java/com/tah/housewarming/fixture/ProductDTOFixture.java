package com.tah.housewarming.fixture;

import com.tah.housewarming.domain.Link;
import com.tah.housewarming.dto.ProductDTO;
import com.tah.housewarming.util.RandomGenerator;

import java.util.List;

public class ProductDTOFixture {
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private String source;
    private List<Link> linksList;
    private List<String> categories;

    public static ProductDTOFixture get() {
        return new ProductDTOFixture();
    }

    public ProductDTO build() {
        return new ProductDTO(
                id
                , name
                , description
                , brand
                , count
                , source
                , linksList
                , categories
        );
    }

    public ProductDTOFixture random() {
        this.id = RandomGenerator.integer();
        this.name = RandomGenerator.string();
        this.description = RandomGenerator.string(654);
        this.brand = RandomGenerator.string(16);
        this.count = RandomGenerator.integer(50);
        this.categories = RandomGenerator.stringList();
        return this;
    }

    public ProductDTOFixture withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProductDTOFixture withName(String name) {
        this.name = name;
        return this;
    }

    public ProductDTOFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDTOFixture withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ProductDTOFixture withCount(Integer count) {
        this.count = count;
        return this;
    }

    public ProductDTOFixture withLinks(List<Link> links) {
        this.linksList = links;
        return this;
    }

    public ProductDTOFixture withCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }
}
