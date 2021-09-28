package com.cts.capstone.dao;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.exception.CreationException;
import com.cts.capstone.exception.NotFoundException;
import com.cts.capstone.model.Customer;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerDaoTest {

	CustomerDao customerDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customerDao = new CustomerDaoImpl(nJdbcTemplate);
		when(nJdbcTemplate.getJdbcTemplate()).thenReturn(jdbcTemplate);
	}

	@Test
	void getCustomerTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString()))
				.thenReturn(CustomerBuilder.of("id1", "companyName", "contactName", "street", "city", "state"));

		Customer c = customerDao.getCustomer("id1");

		assertEquals("id1", c.getCustomerId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString());
	}

	@Test
	void getCustomerTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString()))
				.thenThrow(new EmptyResultDataAccessException(1));

		assertThrows(NotFoundException.class, () -> customerDao.getCustomer("id1"));

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString());
	}

	@Test
	void getAllCategoriesTest() {
		List<Customer> expectedList = new CustomerBuilder()
				.w("id1", "companyName", "contactName", "street", "city", "state")
				.w("id2", "companyName2", "contactName2", "street2", "city2", "state2")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any()))
				.thenReturn(expectedList);

		List<Customer> list = customerDao.getAllCustomers();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any());
	}

	@Test
	void getAllCategoriesTest_empty() {
		List<Customer> expectedList = new ArrayList<>();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any()))
				.thenReturn(expectedList);

		List<Customer> list = customerDao.getAllCustomers();

		assertEquals(0, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any());
	}

	@Test
	void createCustomer() {
		Customer expected = CustomerBuilder.of("id123", "company", "contact", "street", "city", "state");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = customerDao.createCustomer(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createCustomer_duplicateID() {
		Customer expected = CustomerBuilder.of("id123", "company", "contact", "street", "city", "state");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		assertThrows(CreationException.class, () -> customerDao.createCustomer(expected));

		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateCustomer() {
		Customer expected = CustomerBuilder.of("id123", "company", "contact", "street", "city", "state");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = customerDao.updateCustomer(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateCustomer_IdNotFound() {
		Customer expected = CustomerBuilder.of("id123", "company", "contact", "street", "city", "state");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = customerDao.updateCustomer(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteCustomer() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = customerDao.deleteCustomer("id123");

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteCustomer_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = customerDao.deleteCustomer("id123");

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}
}