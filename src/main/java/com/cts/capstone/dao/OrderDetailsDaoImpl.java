package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;
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
public class OrderDetailsDaoImpl implements OrderDetailsDao {

	private static final BeanPropertyRowMapper<OrderDetails> rowMapper = new BeanPropertyRowMapper<>(OrderDetails.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public OrderDetailsDaoImpl() {
	}

	public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate nJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.nJdbcTemplate = nJdbcTemplate;
	}

	@Override
	public boolean createOrderDetails(OrderDetails od) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO orderdetails(orderId, productId, quantity) " +
							"VALUES(:orderId, :productId, :quantity)",
					new BeanPropertySqlParameterSource(od));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM orderdetails WHERE orderid=?", rowMapper, orderId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return jdbcTemplate.query("SELECT * FROM orderdetails", rowMapper);
	}

	@Override
	public boolean updateOrderDetails(OrderDetails od) {
		int i = nJdbcTemplate.update(
				"UPDATE orderdetails " +
						"SET productId=:productId, quantity=:quantity " +
						"WHERE orderId=:orderId",
				new BeanPropertySqlParameterSource(od)
		);
		return i == 1;
	}

	@Override
	public boolean deleteOrderDetails(long id) {
		int i = jdbcTemplate.update(
				"DELETE FROM orderdetails " +
						"WHERE orderId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}
}
