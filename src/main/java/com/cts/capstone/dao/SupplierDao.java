package com.cts.capstone.dao;

import com.cts.capstone.bean.Supplier;

import java.util.List;

public interface SupplierDao {

	Supplier getSupplier(long supplierId);

	List<Supplier> getAllSuppliers();
}
