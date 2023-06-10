package com.tah.housewarming.fixture;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.dto.CreateProductDTO;
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


}
