package com.cts.capstone.dao;

import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.exception.CreationException;
import com.cts.capstone.exception.NotFoundException;
import com.cts.capstone.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {

	ProductDao productDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productDao = new ProductDaoImpl(nJdbcTemplate);
		when(nJdbcTemplate.getJdbcTemplate()).thenReturn(jdbcTemplate);
	}

	@Test
	void getProductTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong()))
				.thenReturn(ProductBuilder.of(1L, "name", 2L, 3L, "4"));

		Product p = productDao.getProduct(1L);

		assertEquals(1L, p.getProductId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong());
	}

	@Test
	void getProductTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		assertThrows(NotFoundException.class, () -> productDao.getProduct(1L));

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong());
	}

	@Test
	void getAllProductsTest() {
		List<Product> expectedList = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(5L, "name2", 5L, 7L, "8")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any()))
				.thenReturn(expectedList);

		List<Product> list = productDao.getAllProducts();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any());
	}

	@Test
	void createProduct() {
		Product expected = ProductBuilder.of(1L, "name", 2L, 3L, "4");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = productDao.createProduct(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createProduct_duplicateID() {
		Product expected = ProductBuilder.of(1L, "name", 2L, 3L, "4");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		assertThrows(CreationException.class, () -> productDao.createProduct(expected));

		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateProduct() {
		Product expected = ProductBuilder.of(1L, "name", 2L, 3L, "4");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = productDao.updateProduct(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateProduct_IdNotFound() {
		Product expected = ProductBuilder.of(1L, "name", 2L, 3L, "4");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = productDao.updateProduct(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteProduct() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = productDao.deleteProduct(1);

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteProduct_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = productDao.deleteProduct(1);

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void getAllProductsByCategoryId() {
		List<Product> expected = new ProductBuilder()
				.w(1, "name", 1, 1, "1")
				.w(2, "name2", 4, 1, "4")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong()))
				.thenReturn(expected);

		List<Product> actual = productDao.getAllProductsByCategoryId(1);

		assertEquals(expected, actual);
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong());
	}
}