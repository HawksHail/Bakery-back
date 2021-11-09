package com.cts.capstone.service;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.model.Category;
import com.cts.capstone.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CategoryServiceTest {

	@Mock
	CategoryRepository repository;

	CategoryService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new CategoryService(repository);
	}

	@Test
	void setCategoryRepository() {
		service.setCategoryRepository(null);
		assertNull(service.getCategoryRepository());
	}

	@Test
	void findAll() {
		List<Category> expected = new CategoryBuilder()
				.w(123, "name", "description")
				.w(124, "name2", "description2")
				.build();
		when(repository.findAllByOrderByCategoryNameAsc())
				.thenReturn(expected);

		List<Category> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAllByOrderByCategoryNameAsc();
	}

	@Test
	void findById() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		Category actual = service.findById(123L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		Category actual = service.findById(123L);

		assertNull(actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void add() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(repository.save(any(Category.class)))
				.thenReturn(expected);

		Category actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Category.class));
	}

	@Test
	void delete() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		boolean actual = service.delete(expected.getId());

		assertTrue(actual);
		verify(repository).deleteById(anyLong());
	}

	@Test
	void deleteNotFound() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		boolean actual = service.delete(expected.getId());

		assertFalse(actual);
		verify(repository, times(0)).deleteById(anyLong());
	}
}