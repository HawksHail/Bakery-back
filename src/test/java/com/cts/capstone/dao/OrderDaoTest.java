package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
				.thenReturn(new Order(1L, "id1", LocalDate.of(2020, 9, 17)));

		Order o = orderDao.getOrder(1L);

		assertEquals(1L, o.getOrderId());
		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong());
	}

	@Test
	void getOrderTest_fail() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong()))
				.thenThrow(new RuntimeException("ID not found"));

		try {
			Order o = orderDao.getOrder(1L);
			fail("Exception expected");
		} catch (RuntimeException ignored) {

		}

		verify(jdbcTemplate, times(1))
				.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any(), anyLong());
	}

	@Test
	void getAllOrdersTest() {
		ArrayList<Order> expectedList = new ArrayList<>();
		expectedList.add(new Order(1L, "id1", LocalDate.of(2020, 9, 17)));
		expectedList.add(new Order(2L, "id2", LocalDate.of(2020, 9, 16)));
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any()))
				.thenReturn(expectedList);

		List<Order> list = orderDao.getAllOrders();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any());
	}
}