package com.tah.housewarming.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryProduct {
    private Integer id;
    private Integer categoryId;
    private Integer productId;
}
