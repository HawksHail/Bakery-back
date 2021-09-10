package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;
import com.cts.capstone.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Order getOrder(long orderId) {
		return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE orderid=?", new BeanPropertyRowMapper<>(Order.class), orderId);
	}
}
