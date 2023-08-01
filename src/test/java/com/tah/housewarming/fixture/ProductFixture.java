package com.tah.housewarming.fixture;

import com.tah.housewarming.domain.Link;
import com.tah.housewarming.domain.Product;
import com.tah.housewarming.util.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFixture {
    private Integer id;
    private String name;
    private String description;
    private String source;
    private String brand;
    private List<Link> linksList;

    public static ProductFixture get() {
        return new ProductFixture();
    }

    public Product build() {
        return new Product(
                id
                , name
                , description
                , source
                , brand
                , linksList
        );
    }

    public ProductFixture random() {
        this.id = RandomGenerator.integer();
        this.name = RandomGenerator.string();
        this.description = RandomGenerator.string(654);
        this.brand = RandomGenerator.string(16);
//        this.linksList = RandomGenerator.stringList();

        return this;
    }

    public ProductFixture withId(Integer id) {
        this.id = id;

        return this;
    }

    public ProductFixture withName(String name) {
        this.name = name;

        return this;
    }

    public ProductFixture withDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductFixture withBrand(String brand) {
        this.brand = brand;

        return this;
    }

    public ProductFixture withLinks(List<Link> links) {
        this.linksList = links;

        return this;
    }
}
