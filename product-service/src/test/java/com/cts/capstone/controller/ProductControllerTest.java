package com.cts.capstone.controller;

import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

	@Mock
	ProductService service;

	ProductController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new ProductController(service);
	}

	@Test
	void getAllProducts() {
		List<Product> expected = new ProductBuilder()
				.w(123, "name", 1234, 1234, "123")
				.w(124, "name2", 1235, 1234, "321")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Product> actual = controller.getAllProducts();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getProduct() {
		Product expected = ProductBuilder.of(123, "name", 1234, 1234, "123");
		when(service.findById(anyLong()))
				.thenReturn(expected);

		Product actual = controller.getProduct(123L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addProduct() {
		Product expected = ProductBuilder.of(123, "name", 1234, 1234, "123");
		when(service.add(any(Product.class)))
				.thenReturn(expected);

		Product actual = controller.addProduct(expected);

		assertEquals(expected, actual);
		verify(service, times(1)).add(any(Product.class));
	}

	@Test
	void getAllProductsInCategory() {
		List<Product> expected = new ProductBuilder()
				.w(123, "name", 1234, 1234, "123")
				.w(124, "name2", 1235, 1234, "321")
				.build();
		when(service.findAllByCategoryId(anyLong()))
				.thenReturn(expected);

		List<Product> actual = controller.getAllProductsInCategory(1234L);

		assertEquals(expected, actual);
		verify(service, times(1)).findAllByCategoryId(anyLong());
	}
}