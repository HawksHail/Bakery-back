package com.cts.capstone.dao;

import com.cts.capstone.bean.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDaoImpl implements SupplierDao {

	private static final BeanPropertyRowMapper<Supplier> rowMapper = new BeanPropertyRowMapper<>(Supplier.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public SupplierDaoImpl() {
	}

	public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Supplier getSupplier(long supplierId) {
		return jdbcTemplate.queryForObject("SELECT * FROM suppliers WHERE supplierid=?", rowMapper, supplierId);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return jdbcTemplate.query("SELECT * FROM suppliers", rowMapper);
	}
}
