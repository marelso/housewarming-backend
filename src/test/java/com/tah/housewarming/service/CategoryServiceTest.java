package com.tah.housewarming.service;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.hamcrest.MatcherAssert.assertThat;

import com.tah.housewarming.domain.Category;
import com.tah.housewarming.fixture.CategoryFixture;
import com.tah.housewarming.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService subject;

    @Test
    public void shouldFindAllCategoriesCorrectly() {
        var given = List.of(CategoryFixture.get().random().build());

        given(repository.findAll()).willReturn(given);

        var result = subject.findAll();

        assertThat(result, equalTo(given));
    }

    @Test
    public void shouldFindGivenIdInDatabase() {
        var id = 1;
        var given = CategoryFixture.get().random().withId(id).build();

        given(repository.findById(id)).willReturn(Optional.of(given));

        var result = subject.findById(id);

        assertThat(result.getId(), equalTo(id));
    }
}