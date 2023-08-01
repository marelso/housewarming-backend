package com.tah.housewarming.dto;

import com.tah.housewarming.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private String brand;
    private Integer count;
    private String source;
    private List<Link> linksList;
    private List<String> categories;
}
