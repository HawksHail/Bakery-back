package com.cts.capstone.dao;

import com.cts.capstone.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public CustomerDaoImpl() {
		//Empty
	}

	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public CustomerDaoImpl(NamedParameterJdbcTemplate nJdbcTemplate) {
		this.nJdbcTemplate = nJdbcTemplate;
	}

	@Override
	public boolean createCustomer(Customer c) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO customers(customerId, companyName, contactName, street, city, state) " +
							"VALUES(:customerId, :companyName, :contactName, :street, :city, :state)",
					new BeanPropertySqlParameterSource(c));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public Customer getCustomer(String customerId) {
		try {
			return nJdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM customers WHERE customerid=?", rowMapper, customerId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM customers", rowMapper);
	}

	@Override
	public boolean updateCustomer(Customer c) {
		int i = nJdbcTemplate.update(
				"UPDATE customers " +
						"SET customerId=:customerId, companyName=:companyName,  contactName=:contactName, street=:street, city=:city, state=:state " +
						"WHERE customerId=:customerId",
				new BeanPropertySqlParameterSource(c)
		);
		return i == 1;
	}

	@Override
	public boolean deleteCustomer(String id) {
		int i = nJdbcTemplate.getJdbcTemplate().update(
				"DELETE FROM customers " +
						"WHERE customerId=?",
				ps -> ps.setString(1, id)
		);
		return i == 1;
	}


}
