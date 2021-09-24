package com.cts.capstone.dao;

import com.cts.capstone.bean.Supplier;
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
public class SupplierDaoImpl implements SupplierDao {

	private static final BeanPropertyRowMapper<Supplier> rowMapper = new BeanPropertyRowMapper<>(Supplier.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate nJdbcTemplate;

	public SupplierDaoImpl() {
	}

	public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SupplierDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate nJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.nJdbcTemplate = nJdbcTemplate;
	}

	@Override
	public boolean createSupplier(Supplier s) {
		int i;
		try {
			i = nJdbcTemplate.update(
					"INSERT INTO suppliers(supplierId, companyName, contactName) " +
							"VALUES(:supplierId, :companyName, :contactName)",
					new BeanPropertySqlParameterSource(s));
		} catch (DuplicateKeyException e) {
			return false;
		}
		return i == 1;
	}

	@Override
	public Supplier getSupplier(long supplierId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM suppliers WHERE supplierid=?", rowMapper, supplierId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return jdbcTemplate.query("SELECT * FROM suppliers", rowMapper);
	}

	@Override
	public boolean updateSupplier(Supplier s) {
		int i = nJdbcTemplate.update(
				"UPDATE suppliers " +
						"SET companyName=:companyName, contactName=:contactName " +
						"WHERE supplierId=:supplierId",
				new BeanPropertySqlParameterSource(s)
		);
		return i == 1;
	}

	@Override
	public boolean deleteSupplier(long id) {
		int i = jdbcTemplate.update(
				"DELETE FROM suppliers " +
						"WHERE supplierId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}
}
