package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
				.thenReturn(new Customer("id1", "companyName", "contactName", "street", "city", "state"));

		Customer c = customerDao.getCustomer("id1");

		assertEquals("id1", c.getCustomerId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString());
	}

	@Test
	void getCustomerTest_fail() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString()))
				.thenThrow(new RuntimeException("ID not found"));

		try {
			Customer c = customerDao.getCustomer("id1");
			fail("Exception expected");
		} catch (RuntimeException ignored) {

		}

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any(), anyString());
	}

	@Test
	void getAllCategoriesTest() {
		ArrayList<Customer> expectedList = new ArrayList<>();
		expectedList.add(new Customer("id1", "companyName", "contactName", "street", "city", "state"));
		expectedList.add(new Customer("id2", "companyName2", "contactName2", "street2", "city2", "state2"));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any()))
				.thenReturn(expectedList);

		List<Customer> list = customerDao.getAllCustomers();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any());
	}

	@Test
	void getAllCategoriesTest_empty() {
		ArrayList<Customer> expectedList = new ArrayList<>();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any()))
				.thenReturn(expectedList);

		List<Customer> list = customerDao.getAllCustomers();

		assertEquals(0, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Customer>>any());
	}
}