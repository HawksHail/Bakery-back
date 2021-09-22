package com.cts.capstone.dao;

import com.cts.capstone.bean.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryDaoTest {

	CategoryDao categoryDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		categoryDao = new CategoryDaoImpl(jdbcTemplate);
	}

	@Test
	void getCategoryTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong()))
				.thenReturn(new Category(1, "name", "description"));

		Category c = categoryDao.getCategory(1);

		assertEquals(1, c.getCategoryId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong());
	}

	@Test
	void getCategoryTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		Category c = categoryDao.getCategory(1);

		assertNull(c);
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong());
	}

	@Test
	void getAllCategoriesTest() {
		ArrayList<Category> expectedList = new ArrayList<>();
		expectedList.add(new Category(1, "name", "description"));
		expectedList.add(new Category(2, "name2", "description2"));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any()))
				.thenReturn(expectedList);

		List<Category> list = categoryDao.getAllCategories();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any());
	}

	@Test
	void getAllCategoriesTest_empty() {
		ArrayList<Category> expectedList = new ArrayList<>();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any()))
				.thenReturn(expectedList);

		List<Category> list = categoryDao.getAllCategories();

		assertEquals(0, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any());
	}
}