package com.cts.capstone.controller;

import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.model.Supplier;
import com.cts.capstone.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SupplierControllerTest {

	@Mock
	SupplierService service;

	SupplierController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new SupplierController(service);
	}

	@Test
	void getAllCategories() {
		List<Supplier> expected = new SupplierBuilder()
				.w(123, "company", "contact")
				.w(124, "company2", "contact2")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Supplier> actual = controller.getAllSuppliers();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getSupplier() {
		Supplier expected = SupplierBuilder.of(123, "company", "contact");
		when(service.findById(anyLong()))
				.thenReturn(expected);

		Supplier actual = controller.getSupplier(123L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addSupplier() {
		Supplier expected = SupplierBuilder.of(123, "company", "contact");
		when(service.add(any(Supplier.class)))
				.thenReturn(expected);

		Supplier actual = controller.addSupplier(expected).getBody();

		assertEquals(expected, actual);
		verify(service, times(1)).add(any(Supplier.class));
	}
}