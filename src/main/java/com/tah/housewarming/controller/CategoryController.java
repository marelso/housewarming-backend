package com.tah.housewarming.controller;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Api(tags = "Category", description = "CRUD of categories.")
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/{id}")
    public Category get(@PathVariable("categoryId") Integer id) {
        return this.service.findById(id);
    }
}
