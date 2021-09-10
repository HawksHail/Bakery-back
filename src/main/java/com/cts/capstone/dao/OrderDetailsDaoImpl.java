package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	private static final BeanPropertyRowMapper<OrderDetails> rowMapper = new BeanPropertyRowMapper<>(OrderDetails.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		return jdbcTemplate.queryForObject("SELECT * FROM orderdetails WHERE orderid=?", rowMapper, orderId);
	}
}
