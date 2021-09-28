package com.cts.capstone.dao;

import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.exception.CreationException;
import com.cts.capstone.exception.NotFoundException;
import com.cts.capstone.model.Supplier;
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

class SupplierDaoImplTest {

	SupplierDao supplierDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		supplierDao = new SupplierDaoImpl(nJdbcTemplate);
		when(nJdbcTemplate.getJdbcTemplate()).thenReturn(jdbcTemplate);

	}

	@Test
	void getSupplierTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong()))
				.thenReturn(SupplierBuilder.of(1L, "companyName", "contactName"));

		Supplier s = supplierDao.getSupplier(1L);

		assertEquals(1L, s.getSupplierId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong());
	}

	@Test
	void getSupplierTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		assertThrows(NotFoundException.class, () -> supplierDao.getSupplier(1L));

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong());
	}

	@Test
	void getAllSuppliersTest() {
		List<Supplier> expectedList = new SupplierBuilder()
				.w(1L, "companyName", "contactName")
				.w(2L, "companyName2", "contactName2")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any()))
				.thenReturn(expectedList);

		List<Supplier> list = supplierDao.getAllSuppliers();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any());
	}

	@Test
	void createSupplier() {
		Supplier expected = SupplierBuilder.of(1L, "companyName", "contactName");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = supplierDao.createSupplier(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createSupplier_duplicateID() {
		Supplier expected = SupplierBuilder.of(1L, "companyName", "contactName");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		assertThrows(CreationException.class, () -> supplierDao.createSupplier(expected));

		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateSupplier() {
		Supplier expected = SupplierBuilder.of(1L, "companyName", "contactName");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = supplierDao.updateSupplier(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateSupplier_IdNotFound() {
		Supplier expected = SupplierBuilder.of(1L, "companyName", "contactName");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = supplierDao.updateSupplier(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteSupplier() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = supplierDao.deleteSupplier(1);

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteSupplier_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = supplierDao.deleteSupplier(1);

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}
}