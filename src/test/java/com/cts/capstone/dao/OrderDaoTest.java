package com.cts.capstone.dao;

import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.model.Order;
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

class OrderDaoTest {

	OrderDao orderDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderDao = new OrderDaoImpl(nJdbcTemplate);
		when(nJdbcTemplate.getJdbcTemplate()).thenReturn(jdbcTemplate);
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

	@Test
	void createOrder() {
		Order expected = OrderBuilder.of(1L, "id1");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = orderDao.createOrder(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createOrder_duplicateID() {
		Order expected = OrderBuilder.of(1L, "id1");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		boolean b = orderDao.createOrder(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateOrder() {
		Order expected = OrderBuilder.of(1L, "id1");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = orderDao.updateOrder(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateOrder_IdNotFound() {
		Order expected = OrderBuilder.of(1L, "id1");
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = orderDao.updateOrder(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteOrder() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = orderDao.deleteOrder(1);

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteOrder_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = orderDao.deleteOrder(1);

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void getOrdersForCustomer() {
		List<Order> expectedList = new OrderBuilder()
				.w(1L, "id123")
				.w(2L, "id123")
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any()))
				.thenReturn(expectedList);

		List<Order> list = orderDao.getAllOrders();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<Order>>any());
	}
}