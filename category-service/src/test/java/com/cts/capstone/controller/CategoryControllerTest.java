package com.cts.capstone.controller;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.model.Category;
import com.cts.capstone.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void addCategory() {
		Category expected = CategoryBuilder.of(123, "name", "description");
		when(service.add(any(Category.class)))
				.thenReturn(expected);

		Category actual = controller.addCategory(expected).getBody();

		assertEquals(expected, actual);
		verify(service, times(1)).add(any(Category.class));
	}
}