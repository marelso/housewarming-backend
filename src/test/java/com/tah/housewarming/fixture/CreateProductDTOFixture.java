package com.tah.housewarming.fixture;

import com.tah.housewarming.domain.Link;
import com.tah.housewarming.domain.Product;
import com.tah.housewarming.dto.CreateProductDTO;
import com.tah.housewarming.util.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductDTOFixture {
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private String source;
    private List<Link> linksList;
    private List<Integer> categories;

    public static CreateProductDTOFixture get() {
        return new CreateProductDTOFixture();
    }

    public CreateProductDTO build() {
        return new CreateProductDTO(
                name
                , description
                , brand
                , count
                , source
                , linksList
                , categories
        );
    }

    public CreateProductDTOFixture random() {
        this.name = RandomGenerator.string();
        this.description = RandomGenerator.string(654);
        this.brand = RandomGenerator.string(16);
        this.count = RandomGenerator.integer(50);
        this.categories = RandomGenerator.integerList();
        return this;
    }

    public CreateProductDTOFixture withName(String name) {
        this.name = name;
        return this;
    }

    public CreateProductDTOFixture withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateProductDTOFixture withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CreateProductDTOFixture withCount(Integer count) {
        this.count = count;
        return this;
    }

    public CreateProductDTOFixture withLinks(List<Link> links) {
        this.linksList = links;
        return this;
    }

    public CreateProductDTOFixture withCategories(List<Integer> categories) {
        this.categories = categories;
        return this;
    }
}
