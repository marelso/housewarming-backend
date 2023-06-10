package com.tah.housewarming.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductDTO {
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private List<String> linksList;
    private List<Integer> categories;
}
