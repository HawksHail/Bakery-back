package com.cts.capstone.repository;

import com.cts.capstone.bean.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
	Optional<Supplier> findByCompanyName(String name);
}
