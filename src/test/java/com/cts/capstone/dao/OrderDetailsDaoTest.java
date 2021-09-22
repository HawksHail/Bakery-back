package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;
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

class OrderDetailsDaoTest {

	OrderDetailsDao orderDetailsDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderDetailsDao = new OrderDetailsDaoImpl(jdbcTemplate);
	}

	@Test
	void getOrderDetailsTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any(), anyLong()))
				.thenReturn(new OrderDetails(1L, 2L, 5));

		OrderDetails od = orderDetailsDao.getOrderDetails(1L);

		assertEquals(1L, od.getOrderId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any(), anyLong());
	}

	@Test
	void getOrderDetailsTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		OrderDetails od = orderDetailsDao.getOrderDetails(1L);

		assertNull(od);
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any(), anyLong());
	}

	@Test
	void getAllOrderDetailssTest() {
		ArrayList<OrderDetails> expectedList = new ArrayList<>();
		expectedList.add(new OrderDetails(1L, 2L, 5));
		expectedList.add(new OrderDetails(3L, 4L, 7));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any()))
				.thenReturn(expectedList);

		List<OrderDetails> list = orderDetailsDao.getAllOrderDetails();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any());
	}

}