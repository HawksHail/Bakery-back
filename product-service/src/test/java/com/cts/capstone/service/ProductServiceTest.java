//package com.cts.capstone.service;
//
//import com.cts.capstone.builder.ProductBuilder;
//import com.cts.capstone.model.Product;
//import com.cts.capstone.repository.ProductRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.*;
//
//
//class ProductServiceTest {
//
//	@Mock
//	ProductRepository repository;
//
//	ProductService service;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//		service = new ProductService(repository);
//	}
//
//	@Test
//	void findAll() {
//		List<Product> expected = new ProductBuilder()
//				.w(123, "name", 1234, 1234, "123")
//				.w(124, "name2", 1235, 1235, "321")
//				.build();
//		when(repository.findAll())
//				.thenReturn(expected);
//
//		List<Product> actual = service.findAll();
//
//		assertEquals(expected, actual);
//		verify(repository, times(1)).findAll();
//	}
//
//	@Test
//	void findById() {
//		Product expected = ProductBuilder.of(123, "name", 1234, 1234, "123");
//		when(repository.findById(anyLong()))
//				.thenReturn(java.util.Optional.of(expected));
//
//		Product actual = service.findById(123L);
//
//		assertEquals(expected, actual);
//		verify(repository, times(1)).findById(anyLong());
//	}
//
//	@Test
//	void findByIdNotFound() {
//		when(repository.findById(anyLong()))
//				.thenReturn(java.util.Optional.empty());
//
//		Product actual = service.findById(123L);
//
//		assertNull(actual);
//		verify(repository, times(1)).findById(anyLong());
//	}
//
//	@Test
//	void add() {
//		Product expected = ProductBuilder.of(123, "name", 1234, 1234, "123");
//		when(repository.save(any(Product.class)))
//				.thenReturn(expected);
//
//		Product actual = service.add(expected);
//
//		assertEquals(expected, actual);
//		verify(repository, times(1)).save(any(Product.class));
//	}
//
//	@Test
//	void findAllByCategoryId() {
//		List<Product> expected = new ProductBuilder()
//				.w(123, "name", 1234, 1234, "123")
//				.w(124, "name2", 1235, 1234, "321")
//				.build();
//		when(repository.findAllByCategoryId(anyLong()))
//				.thenReturn(expected);
//
//		List<Product> actual = service.findAllByCategoryId(1234L);
//
//		assertEquals(expected, actual);
//		verify(repository, times(1)).findAllByCategoryId(anyLong());
//	}
//}