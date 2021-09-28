package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;
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
public class OrderDaoImpl implements OrderDao {

	private static final BeanPropertyRowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public OrderDaoImpl() {
		//Empty
	}

	public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public OrderDaoImpl(NamedParameterJdbcTemplate nJdbcTemplate) {
		this.nJdbcTemplate = nJdbcTemplate;
	}

	@Override
	public boolean createOrder(Order o) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO orders(orderId, customerId, orderDate) " +
							"VALUES(:orderId, :customerId, :orderDate)",
					new BeanPropertySqlParameterSource(o));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public Order getOrder(long orderId) {
		try {
			return nJdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM orders WHERE orderid=?", rowMapper, orderId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Order> getAllOrders() {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM orders", rowMapper);
	}

	@Override
	public boolean updateOrder(Order o) {
		int i = nJdbcTemplate.update(
				"UPDATE orders " +
						"SET customerId=:customerId, orderDate=:orderDate " +
						"WHERE orderId=:orderId",
				new BeanPropertySqlParameterSource(o)
		);
		return i == 1;
	}

	@Override
	public boolean deleteOrder(long id) {
		int i = nJdbcTemplate.getJdbcTemplate().update(
				"DELETE FROM orders " +
						"WHERE orderId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}

	@Override
	public List<Order> getOrdersForCustomer(String userId) {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM orders WHERE customerid=?", rowMapper, userId);
	}
}
