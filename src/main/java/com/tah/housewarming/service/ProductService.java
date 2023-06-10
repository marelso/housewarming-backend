package com.tah.housewarming.service;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.dto.CreateProductDTO;
import com.tah.housewarming.dto.ProductDTO;
import com.tah.housewarming.dto.factory.ProductFactory;
import com.tah.housewarming.exception.IncorrectValueException;
import com.tah.housewarming.exception.NotFoundException;
import com.tah.housewarming.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductFactory factory;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    public Product findById(Integer id) {
        return get(id)
                .orElseThrow(() -> new RuntimeException("There is no product with id: " + id));
    }

    private Optional<Product> get(Integer id) {
        return this.repository.findById(id);
    }

    public List<Product> findAll() {
        return this.repository.findAll();
    }

    public ProductDTO create(CreateProductDTO given) {
        if(productAlreadyExist(given.getName(), given.getBrand()))
            throw new IncorrectValueException("This product already exist on database");

        if(!categoryDoesExist(given.getCategories())) {
            throw new NotFoundException("Invalid category list");
        }

        var product = factory.from(given);

        return factory.from(this.repository.save(product), new ArrayList<String>());
    }

    private Boolean productAlreadyExist(String product, String productBrand) {
        var name = repository.findByName(product);
        var brand = repository.findByBrand(productBrand);

        return name.isPresent() && brand.isPresent();
    }

    private Boolean categoryDoesExist(List<Integer> ids) {
        ids.forEach(categoryService::findById);

        return true;
    }
}
