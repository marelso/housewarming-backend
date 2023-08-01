package com.tah.housewarming.dto;

import com.tah.housewarming.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private String source;
    private List<Link> linksList;
    private List<Integer> categories;
}
