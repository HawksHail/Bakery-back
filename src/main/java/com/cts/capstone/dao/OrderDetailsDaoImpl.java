package com.cts.capstone.dao;

import com.cts.capstone.exception.CreationException;
import com.cts.capstone.exception.NotFoundException;
import com.cts.capstone.model.OrderDetails;
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
	NamedParameterJdbcTemplate nJdbcTemplate;

	public OrderDetailsDaoImpl() {
		//Empty
	}

	public OrderDetailsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public OrderDetailsDaoImpl(NamedParameterJdbcTemplate nJdbcTemplate) {
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
			throw new CreationException("order details", od.getOrderId(), e);
		}
		return i == 1;
	}

	@Override
	public boolean createOrderDetailsList(OrderDetails[] details) {
		int[] i;
		try {
			BeanPropertySqlParameterSource[] arr = new BeanPropertySqlParameterSource[details.length];
			for (int j = 0; j < details.length; j++) {
				arr[j] = new BeanPropertySqlParameterSource(details[j]);
			}
			i = nJdbcTemplate.batchUpdate("INSERT INTO orderdetails(orderId, productId, quantity) " +
					"VALUES(:orderId, :productId, :quantity)", arr);
		} catch (DuplicateKeyException e) {
			throw new CreationException("order details list", details[0].getOrderId(), e);
		}
		return i.length == details.length;
	}

	@Override
	public List<OrderDetails> getOrderDetails(long orderId) {
		try {
			return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM orderdetails WHERE orderid=?", rowMapper, orderId);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("order details", orderId, e);
		}
	}

	@Override
	public OrderDetails getOrderDetails(long orderId, long productId) {
		try {
			return nJdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM orderdetails WHERE orderid=? AND productid=?", rowMapper, orderId, productId);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("order details", orderId, e);
		}
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM orderdetails", rowMapper);
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
		int i = nJdbcTemplate.getJdbcTemplate().update(
				"DELETE FROM orderdetails " +
						"WHERE orderId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}

	@Override
	public List<OrderDetails> getOrderDetailsForOrder(long orderId) {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM orderdetails WHERE orderId=?", rowMapper, orderId);
	}
}
