package com.cts.capstone.dao;

import com.cts.capstone.bean.Category;
import com.cts.capstone.builder.CategoryBuilder;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryDaoTest {

	CategoryDao categoryDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		categoryDao = new CategoryDaoImpl(jdbcTemplate, nJdbcTemplate);
	}

	@Test
	void getCategory() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong()))
				.thenReturn(CategoryBuilder.of(1, "name", "description"));

		Category c = categoryDao.getCategory(1);

		assertEquals(1, c.getCategoryId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong());
	}

	@Test
	void getCategory_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		Category c = categoryDao.getCategory(1);

		assertNull(c);
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any(), anyLong());
	}

	@Test
	void getAllCategories() {
		List<Category> expectedList = new CategoryBuilder()
				.w(1, "name", "description")
				.w(2, "name2", "description2")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any()))
				.thenReturn(expectedList);

		List<Category> list = categoryDao.getAllCategories();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any());
	}

	@Test
	void getAllCategories_empty() {
		List<Category> expectedList = new ArrayList<>();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any()))
				.thenReturn(expectedList);

		List<Category> list = categoryDao.getAllCategories();

		assertEquals(0, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Category>>any());
	}

	@Test
	void createCategory() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = categoryDao.createCategory(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createCategory_duplicateID() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		boolean b = categoryDao.createCategory(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateCategory() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = categoryDao.updateCategory(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateCategory_IdNotFound() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = categoryDao.updateCategory(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteCategory() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = categoryDao.deleteCategory(1);

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteCategory_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = categoryDao.deleteCategory(1);

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}
}