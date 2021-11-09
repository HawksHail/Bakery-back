package com.cts.capstone.service;

import com.cts.capstone.model.Supplier;
import com.cts.capstone.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SupplierService {

	private SupplierRepository supplierRepository;

	public SupplierService(SupplierRepository supplierRepository) {
		super();
		this.supplierRepository = supplierRepository;
	}

	public SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}

	public void setSupplierRepository(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
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

	public boolean delete(Long id) {
		Optional<Supplier> supplier = supplierRepository.findById(id);
		if (supplier.isPresent()) {
			supplierRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
