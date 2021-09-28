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
	NamedParameterJdbcTemplate nJdbcTemplate;

	public SupplierDaoImpl() {
		//Empty
	}

	public SupplierDaoImpl(JdbcTemplate jdbcTemplate) {
		this.nJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	public SupplierDaoImpl(NamedParameterJdbcTemplate nJdbcTemplate) {
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
			return nJdbcTemplate.getJdbcTemplate().queryForObject("SELECT * FROM suppliers WHERE supplierid=?", rowMapper, supplierId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return nJdbcTemplate.getJdbcTemplate().query("SELECT * FROM suppliers", rowMapper);
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
		int i = nJdbcTemplate.getJdbcTemplate().update(
				"DELETE FROM suppliers " +
						"WHERE supplierId=?",
				ps -> ps.setLong(1, id)
		);
		return i == 1;
	}
}
