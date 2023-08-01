package com.tah.housewarming.controller;

import com.tah.housewarming.dto.CreateProductDTO;
import com.tah.housewarming.dto.ProductDTO;
import com.tah.housewarming.service.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

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

    @GetMapping("/category")
    public List<ProductDTO> findCategoryProducts(@RequestParam(required = true) Integer categoryId) {
        return service.findCategoryProducts(categoryId);
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable("id") Integer id) {
        return this.service.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody CreateProductDTO product) {
        return this.service.create(product);
    }

    @PostMapping("/claim/{id}")
    public ProductDTO claim(@PathVariable("id") Integer id, @RequestParam String username) {
        return service.claim(id, username);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.service.delete(id);
    }
}
