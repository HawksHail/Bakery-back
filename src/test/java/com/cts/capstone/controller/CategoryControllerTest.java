package com.cts.capstone.controller;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.exception.CategoryNotFoundException;
import com.cts.capstone.model.Category;
import com.cts.capstone.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryControllerTest {

	@Mock
	CategoryService service;

	CategoryController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new CategoryController(service);
	}

	@Test
	void getAllCategories() {
		List<Category> expected = new CategoryBuilder()
				.w(123, "name", "description")
				.w(124, "name2", "description2")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Category> actual = controller.getAllCategories();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getCategory() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(service.findById(anyLong()))
				.thenReturn(expected);

		Category actual = controller.getCategory(123L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void getCategoryNotFound() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(service.findById(anyLong()))
				.thenReturn(null);

		assertThrows(CategoryNotFoundException.class, () -> controller.getCategory(123L));

		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addCategory() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(service.add(any(Category.class)))
				.thenReturn(expected);

		ResponseEntity<Category> actual = controller.addCategory(expected);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
		assertTrue(Objects.requireNonNull(actual.getHeaders().get("Location")).get(0).contains(String.valueOf(expected.getId())));
		verify(service, times(1)).add(any(Category.class));
	}

	@Test
	void putCategory() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(service.add(any(Category.class)))
				.thenReturn(expected);

		ResponseEntity<Category> actual = controller.putCategory(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).add(any(Category.class));
	}

	@Test
	void deleteCategory() {
		Category expected = CategoryBuilder.of(123, "name", "description");

		ResponseEntity<Category> actual = controller.deleteCategory(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(any(Category.class));
	}

	@Test
	void deleteCategoryById() {
		ResponseEntity<Category> actual = controller.deleteCategoryById(123L);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(anyLong());
	}
}