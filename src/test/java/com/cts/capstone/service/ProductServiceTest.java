package com.cts.capstone.service;

import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.model.Product;
import com.cts.capstone.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceTest {

	@Mock
	ProductRepository repository;

	ProductService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new ProductService(repository);
	}

	@Test
	void setProductRepository() {
		service.setProductRepository(null);
		assertNull(service.getProductRepository());
	}

	@Test
	void findAll() {
		List<Product> expected = new ProductBuilder()
				.w(123, "name", "123")
				.w(124, "name2", "321")
				.build();
		when(repository.findAllByOrderByProductNameAsc())
				.thenReturn(expected);

		List<Product> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAllByOrderByProductNameAsc();
	}

	@Test
	void findById() {
		Product expected = ProductBuilder.of(123, "name", "123");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		Product actual = service.findById(123L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		Product actual = service.findById(123L);

		assertNull(actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void add() {
		Product expected = ProductBuilder.of(123, "name", "123");
		when(repository.save(any(Product.class)))
				.thenReturn(expected);

		Product actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Product.class));
	}

	@Test
	void delete() {
		Product expected = ProductBuilder.of(123, "name", "123");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		boolean actual = service.delete(123L);

		assertTrue(actual);
		verify(repository).findById(anyLong());
		verify(repository).deleteById(anyLong());
	}

	@Test
	void deleteNotFound() {
		Product expected = ProductBuilder.of(123, "name", "123");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		boolean actual = service.delete(123L);

		assertFalse(actual);
		verify(repository).findById(anyLong());
		verify(repository, times(0)).deleteById(anyLong());
	}
}