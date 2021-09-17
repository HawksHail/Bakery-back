package com.cts.capstone.dao;

import com.cts.capstone.bean.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
				.thenReturn(new Product(1L, "name", 2L, 3L, new BigDecimal("4")));

		Product p = productDao.getProduct(1L);

		assertEquals(1L, p.getProductId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong());
	}

	@Test
	void getProductTest_fail() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong()))
				.thenThrow(new RuntimeException("ID not found"));

		try {
			Product p = productDao.getProduct(1L);
			fail("Exception expected");
		} catch (RuntimeException ignored) {

		}

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any(), anyLong());
	}

	@Test
	void getAllProductsTest() {
		ArrayList<Product> expectedList = new ArrayList<>();
		expectedList.add(new Product(1L, "name", 2L, 3L, new BigDecimal("4")));
		expectedList.add(new Product(5L, "name2", 5L, 7L, new BigDecimal("8")));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any()))
				.thenReturn(expectedList);

		List<Product> list = productDao.getAllProducts();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Product>>any());
	}
}