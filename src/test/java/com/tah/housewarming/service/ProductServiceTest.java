package com.tah.housewarming.service;

import com.tah.housewarming.dto.factory.ProductFactory;
import com.tah.housewarming.exception.IncorrectValueException;
import com.tah.housewarming.exception.NotFoundException;
import com.tah.housewarming.fixture.CategoryFixture;
import com.tah.housewarming.fixture.CreateProductDTOFixture;
import com.tah.housewarming.fixture.ProductDTOFixture;
import com.tah.housewarming.fixture.ProductFixture;
import com.tah.housewarming.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductFactory factory;
    @Mock
    private ProductRepository repository;
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService subject;

    @Test
    public void shouldThrowExceptionWhenSearchingInvalidId() {
        var id = 1;

        given(repository.findById(id)).willReturn(Optional.empty());


        catchException(() -> subject.findById(id));


        assertThat(caughtException(), instanceOf(RuntimeException.class));
        then(repository).should().findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldReturnCorrectObjectWhenSearchingValidId() {
        var id = 1;
        var given = ProductFixture.get().random().withId(id).build();

        given(repository.findById(id)).willReturn(Optional.of(given));


        var result = subject.findById(id);


        assertThat(result, equalTo(given));
    }

    @Test
    public void shouldReturnAllProductsCorrectly() {
        var given = List.of(ProductFixture.get().random().build());

        given(repository.findAll()).willReturn(given);


        var result = subject.findAll();


        assertThat(result, equalTo(given));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingExistingProduct() {
        var product = "product";
        var brand = "brand";
        var existing = ProductFixture.get()
                .random()
                .withName(product)
                .withBrand(brand)
                .build();
        var given = CreateProductDTOFixture.get()
                .random()
                .withName(product)
                .withBrand(brand)
                .build();


        given(repository.findByName(product)).willReturn(Optional.of(existing));
        given(repository.findByBrand(brand)).willReturn(Optional.of(existing));


        catchException(() -> subject.create(given));


        assertThat(caughtException(), instanceOf(IncorrectValueException.class));
    }

    @Test
    public void shouldThrowExceptionWhenCreatingProductToInvalidCategory() {
        var id = 1;
        var categories = List.of(id);
        var given = CreateProductDTOFixture.get()
                .random()
                .withCategories(categories)
                .build();

        given(repository.findByName(given.getName())).willReturn(Optional.empty());
        given(repository.findByBrand(given.getBrand())).willReturn(Optional.empty());
        given(categoryService.findById(id)).willThrow(new NotFoundException("Category" + id));


        catchException(() -> subject.create(given));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
    }

    @Test
    public void shouldCreateProductCorrectly() {
        var id = 1;
        var category = CategoryFixture.get()
                .random()
                .withId(id)
                .build();
        var given = CreateProductDTOFixture.get()
                .random()
                .withCategories(List.of(id))
                .build();
        var product = ProductFixture.get().random().build();
        var expected = ProductDTOFixture.get().random().build();

        given(repository.findByName(given.getName())).willReturn(Optional.empty());
        given(repository.findByBrand(given.getBrand())).willReturn(Optional.empty());
        given(repository.save(product)).willReturn(product);
        given(categoryService.findById(id)).willReturn(category);

        given(factory.from(given)).willReturn(product);
        given(factory.from(product)).willReturn(expected);


        var result = subject.create(given);


        assertThat(result, equalTo(expected));
        then(repository).should().save(product);
    }

    @Test
    public void shouldThrowExceptionWhenDeletingInvalidProduct() {
        var id = 1;

        given(repository.findById(id)).willReturn(Optional.empty());


        catchException(() -> subject.delete(id));


        assertThat(caughtException(), instanceOf(NotFoundException.class));
    }

    @Test
    public void shouldDeleteProductCorrectly() {

    }
}