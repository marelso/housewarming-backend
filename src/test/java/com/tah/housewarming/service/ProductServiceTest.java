package com.tah.housewarming.service;

import com.tah.housewarming.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.googlecode.catchexception.CatchException.catchException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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


    }
}