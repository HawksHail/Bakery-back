package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	private static final BeanPropertyRowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Order getOrder(long orderId) {
		return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE orderid=?", rowMapper, orderId);
	}
}
