package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

	private static final BeanPropertyRowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public OrderDaoImpl() {
	}

	public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Order getOrder(long orderId) {
		return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE orderid=?", rowMapper, orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return jdbcTemplate.query("SELECT * FROM orders", rowMapper);
	}
}
