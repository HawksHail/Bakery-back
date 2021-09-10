package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Customer getCustomer(String customerId) {
		return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE customerid=?", rowMapper, customerId);
	}
}
