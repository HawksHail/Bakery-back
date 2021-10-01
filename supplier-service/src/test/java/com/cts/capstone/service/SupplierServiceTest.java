package com.cts.capstone.service;

import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.exception.SupplierNotFoundException;
import com.cts.capstone.model.Supplier;
import com.cts.capstone.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class SupplierServiceTest {

	@Mock
	SupplierRepository repository;

	SupplierService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new SupplierService(repository);
	}

	@Test
	void findAll() {
		List<Supplier> expected = new SupplierBuilder()
				.w(123, "company", "contact")
				.w(124, "company2", "contact2")
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<Supplier> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		Supplier expected = SupplierBuilder.of(123, "company", "contact");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		Supplier actual = service.findById(123L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		assertThrows(SupplierNotFoundException.class, () -> service.findById(123L));

		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void add() {
		Supplier expected = SupplierBuilder.of(123, "company", "contact");
		when(repository.save(any(Supplier.class)))
				.thenReturn(expected);

		Supplier actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Supplier.class));
	}
}