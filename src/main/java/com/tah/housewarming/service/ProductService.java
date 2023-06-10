package com.tah.housewarming.service;

import com.tah.housewarming.domain.Product;
import com.tah.housewarming.dto.CreateProductDTO;
import com.tah.housewarming.dto.ProductDTO;
import com.tah.housewarming.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

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
            throw new RuntimeException("This product already exist on database");



        return null;
    }

    private Boolean productAlreadyExist(String product, String productBrand) {
        var name = repository.findByName(product);
        var brand = repository.findByBrand(productBrand);

        return name.isPresent() && brand.isPresent();
    }
}
