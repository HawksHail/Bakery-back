package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;
import com.cts.capstone.builder.OrderBuilder;
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

class OrderDaoTest {

	OrderDao orderDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderDao = new OrderDaoImpl(jdbcTemplate);
	}

	@Test
	void getOrderTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong()))
				.thenReturn(OrderBuilder.of(1L, "id1"));

		Order o = orderDao.getOrder(1L);

		assertEquals(1L, o.getOrderId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong());
	}

	@Test
	void getOrderTest_notFound() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong()))
				.thenThrow(new EmptyResultDataAccessException(1));

		Order o = orderDao.getOrder(1L);

		assertNull(o);
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong());
	}

	@Test
	void getAllOrdersTest() {
		List<Order> expectedList = new OrderBuilder()
				.w(1L, "id1")
				.w(2L, "id2")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any()))
				.thenReturn(expectedList);

		List<Order> list = orderDao.getAllOrders();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any());
	}
}