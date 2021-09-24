package com.cts.capstone.dao;

import com.cts.capstone.bean.Category;
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
public class CategoryDaoImpl implements CategoryDao {

	private static final BeanPropertyRowMapper<Category> rowMapper = new BeanPropertyRowMapper<>(Category.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public CategoryDaoImpl() {
	}

	public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public CategoryDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate nJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.nJdbcTemplate = nJdbcTemplate;
	}


	@Override
	public boolean createCategory(Category c) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO categories(categoryId, categoryName, description) " +
							"VALUES(:categoryId, :categoryName, :description)",
					new BeanPropertySqlParameterSource(c));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public Category getCategory(long categoryId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM categories WHERE categoryid=?", rowMapper, categoryId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return jdbcTemplate.query("SELECT * FROM categories", rowMapper);
	}

	@Override
	public boolean updateCategory(Category c) {
		int i = nJdbcTemplate.update(
				"UPDATE categories " +
						"SET categoryName=:categoryName, description=:description " +
						"WHERE categoryId=:categoryId",
				new BeanPropertySqlParameterSource(c)
		);
		return i == 1;
	}

	@Override
	public boolean deleteCategory(long id) {
		int i = jdbcTemplate.update(
				"DELETE FROM categories " +
						"WHERE categoryid=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}
}
