package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;
import com.cts.capstone.builder.CustomerBuilder;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerDaoTest {

	CustomerDao customerDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customerDao = new CustomerDaoImpl(jdbcTemplate);
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

		Customer c = customerDao.getCustomer("id1");

		assertNull(c);
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
}