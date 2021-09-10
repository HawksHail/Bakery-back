package com.cts.capstone.dao;

import com.cts.capstone.bean.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Category getCategory(long categoryId) {
		return jdbcTemplate.queryForObject("SELECT * FROM categories WHERE categoryid=?", new BeanPropertyRowMapper<>(Category.class), categoryId);

	}
}
