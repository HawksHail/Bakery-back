package com.cts.capstone.dao;

import com.cts.capstone.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public CustomerDaoImpl() {
	}

	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Customer getCustomer(String customerId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE customerid=?", rowMapper, customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		return jdbcTemplate.query("SELECT * FROM customers", rowMapper);
	}


}
