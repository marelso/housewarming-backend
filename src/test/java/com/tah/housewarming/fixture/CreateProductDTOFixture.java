package com.tah.housewarming.fixture;

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
    private List<String> linksList;
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
                , linksList
                , categories
        );
    }

    public CreateProductDTOFixture random() {
        this.name = RandomGenerator.string();
        this.description = RandomGenerator.string(654);
        this.brand = RandomGenerator.string(16);
        this.count = RandomGenerator.integer(50);
        this.linksList = RandomGenerator.stringList();
        this.categories = RandomGenerator.integerList();
        return this;
    }
}
