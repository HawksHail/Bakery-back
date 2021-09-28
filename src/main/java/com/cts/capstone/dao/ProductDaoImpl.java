package com.cts.capstone.dao;

import com.cts.capstone.model.Product;
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
public class ProductDaoImpl implements ProductDao {

	private static final BeanPropertyRowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public ProductDaoImpl() {
		//Empty
	}

	public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public ProductDaoImpl(NamedParameterJdbcTemplate nJdbcTemplate) {
		this.nJdbcTemplate = nJdbcTemplate;
	}

	@Override
	public boolean createProduct(Product p) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO products(productId, productName, supplierId, categoryId, unitPrice) " +
							"VALUES(:productId, :productName, :supplierId, :categoryId, :unitPrice)",
					new BeanPropertySqlParameterSource(p));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public Product getProduct(long productId) {
		try {
			return nJdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM products WHERE productid=?", rowMapper, productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM products", rowMapper);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(long categoryId) {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM products WHERE categoryid=?", rowMapper, categoryId);
	}

	@Override
	public boolean updateProduct(Product p) {
		int i = nJdbcTemplate.update(
				"UPDATE products " +
						"SET productName=:productName, supplierId=:supplierId, categoryId=:categoryId, unitPrice=:unitPrice " +
						"WHERE productId=:productId",
				new BeanPropertySqlParameterSource(p)
		);
		return i == 1;
	}

	@Override
	public boolean deleteProduct(long id) {
		int i = nJdbcTemplate.getJdbcTemplate().update(
				"DELETE FROM products " +
						"WHERE productId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}
}
