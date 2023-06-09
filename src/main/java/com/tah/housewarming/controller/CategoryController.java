package com.tah.housewarming.controller;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Api(tags = "Category", description = "CRUD of categories.")
public class CategoryController {
    private final CategoryService service;

    @GetMapping()
    public List<Category> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    public Category get(@PathVariable("id") Integer id) {
        return this.service.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody String name) {
        return this.service.create(name);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) {
        return this.service.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        this.service.delete(id);
    }
}
