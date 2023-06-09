package com.tah.housewarming.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductClaim {
    private Integer id;
    private Integer productId;
    private String username;
    private Integer count;
}
