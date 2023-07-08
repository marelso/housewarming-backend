package com.tah.housewarming.dto.factory;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.dto.CreateProductDTO;
import com.tah.housewarming.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFactory {
    public Product from(CreateProductDTO dto) {
        var product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setBrand(dto.getBrand());
        product.setLinksList(dto.getLinksList());

        return product;
    }

    public ProductDTO from(Product dto, List<String> categories, Integer count) {
        var product = new ProductDTO();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setBrand(dto.getBrand());
        product.setLinksList(dto.getLinksList());
        product.setCategories(categories);
        product.setCount(count);

        return product;
    }
}
