package com.tah.housewarming.service;

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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

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
}