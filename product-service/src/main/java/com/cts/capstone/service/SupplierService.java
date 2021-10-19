package com.cts.capstone.service;

import com.cts.capstone.model.Supplier;
import com.cts.capstone.repository.SupplierRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SupplierService {

	private SupplierRepository supplierRepository;

	public SupplierService(SupplierRepository supplierRepository) {
		super();
		this.supplierRepository = supplierRepository;
	}

	public void setSupplierService(SupplierRepository productRepository) {
		this.supplierRepository = productRepository;
	}

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public Supplier findById(Long id) {
		return supplierRepository.findById(id).orElse(null);
	}

	public Supplier add(Supplier product) {
		return supplierRepository.save(product);
	}

	public boolean delete(Supplier supplier) {
		try {
			supplierRepository.delete(supplier);
		} catch (IllegalArgumentException ex) {
			return false;
		}
		return true;
	}

	public boolean delete(Long id) {
		try {
			supplierRepository.deleteById(id);
		} catch (IllegalArgumentException | EmptyResultDataAccessException ex) {
			return false;
		}
		return true;
	}
}
