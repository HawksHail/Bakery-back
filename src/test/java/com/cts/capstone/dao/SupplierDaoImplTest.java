package com.cts.capstone.dao;

import com.cts.capstone.bean.Supplier;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SupplierDaoImplTest {

	SupplierDao supplierDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		supplierDao = new SupplierDaoImpl(jdbcTemplate);
	}

	@Test
	void getSupplierTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong()))
				.thenReturn(new Supplier(1L, "companyName", "contactName"));

		Supplier s = supplierDao.getSupplier(1L);

		assertEquals(1L, s.getSupplierId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong());
	}

	@Test
	void getSupplierTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));


		Supplier s = supplierDao.getSupplier(1L);

		assertNull(s);
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any(), anyLong());
	}

	@Test
	void getAllSuppliersTest() {
		ArrayList<Supplier> expectedList = new ArrayList<>();
		expectedList.add(new Supplier(1L, "companyName", "contactName"));
		expectedList.add(new Supplier(2L, "companyName2", "contactName2"));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any()))
				.thenReturn(expectedList);

		List<Supplier> list = supplierDao.getAllSuppliers();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Supplier>>any());
	}
}