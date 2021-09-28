package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;
import com.cts.capstone.builder.OrderDetailsBuilder;
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

class OrderDetailsDaoTest {

	OrderDetailsDao orderDetailsDao;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	NamedParameterJdbcTemplate nJdbcTemplate;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderDetailsDao = new OrderDetailsDaoImpl(nJdbcTemplate);
		when(nJdbcTemplate.getJdbcTemplate()).thenReturn(jdbcTemplate);
	}

	@Test
	void getOrderDetailsTest() {
		when(jdbcTemplate.queryForObject(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any(), anyLong()))
				.thenReturn(OrderDetailsBuilder.of(1L, 2L, 5));

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
	void getAllOrderDetailsTest() {
		List<OrderDetails> expectedList = new OrderDetailsBuilder()
				.w(1L, 2L, 5)
				.w(3L, 4L, 7)
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any()))
				.thenReturn(expectedList);

		List<OrderDetails> list = orderDetailsDao.getAllOrderDetails();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any());
	}

	@Test
	void createOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1L, 2L, 5);
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = orderDetailsDao.createOrderDetails(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void createOrderDetails_duplicateID() {
		OrderDetails expected = OrderDetailsBuilder.of(1L, 2L, 5);
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenThrow(new DuplicateKeyException("Duplicate primary key"));

		boolean b = orderDetailsDao.createOrderDetails(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1L, 2L, 5);
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(1);

		boolean b = orderDetailsDao.updateOrderDetails(expected);

		assertTrue(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void updateOrderDetails_IdNotFound() {
		OrderDetails expected = OrderDetailsBuilder.of(1L, 2L, 5);
		when(nJdbcTemplate.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any()))
				.thenReturn(0);

		boolean b = orderDetailsDao.updateOrderDetails(expected);

		assertFalse(b);
		verify(nJdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<BeanPropertySqlParameterSource>any());
	}

	@Test
	void deleteOrderDetails() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(1);

		boolean b = orderDetailsDao.deleteOrderDetails(1);

		assertTrue(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void deleteOrderDetails_IdNotFound() {
		when(jdbcTemplate.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any()))
				.thenReturn(0);

		boolean b = orderDetailsDao.deleteOrderDetails(1);

		assertFalse(b);
		verify(jdbcTemplate, times(1))
				.update(anyString(), ArgumentMatchers.<PreparedStatementSetter>any());
	}

	@Test
	void getOrderDetailsForOrder() {
		List<OrderDetails> expectedList = new OrderDetailsBuilder()
				.w(1L, 2L, 5)
				.w(1L, 4L, 7)
				.build();
		when(jdbcTemplate.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any()))
				.thenReturn(expectedList);

		List<OrderDetails> list = orderDetailsDao.getAllOrderDetails();

		assertEquals(2, list.size());
		verify(jdbcTemplate, times(1))
				.query(anyString(), ArgumentMatchers.<BeanPropertyRowMapper<OrderDetails>>any());
	}
}