package com.tah.housewarming.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private List<String> linksList;
}
