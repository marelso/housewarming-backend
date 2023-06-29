package com.tah.housewarming.controller;

import com.tah.housewarming.dto.ProductDTO;
import com.tah.housewarming.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
@Api(tags = "Product", description = "CRUD of products.")
public class ProductController {
    private final ProductService service;

    @GetMapping()
    public List<ProductDTO> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable("id") Integer id) {
        return this.service.findById(id);
    }
}
