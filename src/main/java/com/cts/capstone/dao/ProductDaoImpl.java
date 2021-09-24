package com.cts.capstone.dao;

import com.cts.capstone.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

	private static final BeanPropertyRowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public ProductDaoImpl() {
	}

	public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Product getProduct(long productId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM products WHERE productid=?", rowMapper, productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return jdbcTemplate.query("SELECT * FROM products", rowMapper);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(long categoryId) {
		return jdbcTemplate.query("SELECT * FROM products WHERE categoryid=?", rowMapper, categoryId);
	}
}
