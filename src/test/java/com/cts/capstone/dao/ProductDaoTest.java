package com.cts.capstone.dao;

import com.cts.capstone.bean.Product;
import com.cts.capstone.builder.ProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductDaoTest {

	ProductDao productDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productDao = new ProductDaoImpl(jdbcTemplate);
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


		Product p = productDao.getProduct(1L);

		assertNull(p);
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
}