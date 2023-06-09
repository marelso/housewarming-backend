package com.tah.housewarming.service;


import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.tah.housewarming.fixture.CategoryFixture;
import com.tah.housewarming.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    public void shouldThrowExceptionWhenThereIsNoGivenIdInDatabase() {
        var id = 1;
        var given = CategoryFixture.get().random().withId(id).build();

        given(repository.findById(id)).willReturn(Optional.empty());


        catchException(() -> subject.findById(id));


        assertThat(caughtException(), instanceOf(RuntimeException.class));
        then(repository).should().findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindGivenIdInDatabase() {
        var id = 1;
        var given = CategoryFixture.get().random().withId(id).build();

        given(repository.findById(id)).willReturn(Optional.of(given));

        var result = subject.findById(id);

        assertThat(result.getId(), equalTo(id));
    }

    @Test
    public void shouldSaveCorrectlyGivenCategory() {
        var given = CategoryFixture.get().random().build();

        given(repository.save(given)).willReturn(given);


        var result = subject.create(given);


        assertThat(result, equalTo(given));
        then(repository).should().save(given);
    }

    @Test
    public void shouldThrowExceptionWhenGivenNameAlreadyExist() {
        var name = "test";
        var given = CategoryFixture.get()
                .random()
                .withName(name)
                .build();

        given(repository.findByName(name)).willReturn(Optional.of(given));


        catchException(() -> subject.create(given));


        assertThat(caughtException(), instanceOf(RuntimeException.class));
    }

    @Test
    public void shouldThrowExceptionWhenGivenIdAlreadyExist() {
        var id = 1;
        var given = CategoryFixture.get()
                .random()
                .withId(id)
                .build();

        given(repository.findById(id)).willReturn(Optional.of(given));


        catchException(() -> subject.create(given));


        assertThat(caughtException(), instanceOf(RuntimeException.class));
    }
}