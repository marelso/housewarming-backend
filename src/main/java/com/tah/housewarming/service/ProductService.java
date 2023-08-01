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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductFactory factory;
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ClaimService claimService;
    private final CategoryProductService relationService;
    private final LinkService linkService;

    public ProductDTO findById(Integer id) {
        var product = findProductById(id);
        var categoriesNames = categoryService.getCategoriesNamesByProduct(id);
        var quantity = claimService.getProductQuantity(id);

        return factory.from(product, categoriesNames, quantity);
    }

    private Product findProductById(Integer id) {
        return get(id).orElseThrow(() -> new NotFoundException("There is no product with id: " + id));
    }

    private Optional<Product> get(Integer id) {
        return this.repository.findById(id);
    }

    public List<ProductDTO> findAll() {
        return this.repository.findAll().stream().map((product) -> {
            var quantity = claimService.getProductQuantity(product.getId());
            var categoriesNames = categoryService.getCategoriesNamesByProduct(product.getId());

            return factory.from(product, categoriesNames, quantity);
        }).collect(Collectors.toList());
    }

    public ProductDTO create(CreateProductDTO given) {
        if (productAlreadyExist(given.getName(), given.getBrand()))
            throw new IncorrectValueException("This product already exist on database");

        if (!categoryDoesExist(given.getCategories())) {
            throw new NotFoundException("Invalid category list");
        }

        given.setLinksList(linkService.create(given.getLinksList()));

        var product = this.repository.save(factory.from(given));

        Integer count = claimService.insert(product, given.getCount());

        var categories = new ArrayList<String>();
        given.getCategories().forEach((categoryId) -> {
            relationService.create(categoryId, product.getId());
            categories.add(categoryService.findById(categoryId).getName());
        });

        return factory.from(product, categories, count);
    }

    private Boolean productAlreadyExist(String product, String productBrand) {
        var name = repository.findFirstByName(product);
        var brand = repository.findFirstByBrand(productBrand);

        return name.isPresent() && brand.isPresent();
    }

    private Boolean categoryDoesExist(List<Integer> ids) {
        ids.forEach(categoryService::findById);

        return true;
    }

    @Transactional
    public void delete(Integer id) {
        var product = findById(id);
        this.linkService.delete(product.getLinksList());
        this.claimService.deleteByProductId(product.getId());
        this.relationService.deleteByProduct(product.getId());
        this.repository.deleteById(product.getId());
    }

    public ProductDTO claim(Integer id, String username) {
        if (!isAvailable(id)) {
            throw new IncorrectValueException("This product is no longer available.");
        }

        claimService.claimProduct(id, username);

        var categories = categoryService.getCategoriesNamesByProduct(id);
        var quantity = claimService.getProductQuantity(id);

        return this.factory.from(findProductById(id), categories, quantity);
    }

    private Boolean isAvailable(Integer id) {
        return get(id).isPresent() && (claimService.isAvailable(id));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findCategoryProducts(Integer categoryId) {
        var validId = categoryService.findById(categoryId).getId();

        var products = this.claimService.findByCategoryId(validId);

        var list = products.stream().map(claim -> this.findById(claim.getProduct().getId())).collect(Collectors.toList());

        return list;
    }
}
