package com.tah.housewarming.service;

import com.tah.housewarming.repository.CategoryProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryProductServiceTest {
    @Mock
    private CategoryProductRepository repository;
    @InjectMocks
    private CategoryProductService subject;
}