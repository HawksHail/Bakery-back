package com.cts.capstone.dao;

import com.cts.capstone.bean.Supplier;

import java.util.List;

public interface SupplierDao {

	boolean createSupplier(Supplier s);

	Supplier getSupplier(long supplierId);

	List<Supplier> getAllSuppliers();

	boolean updateSupplier(Supplier s);

	boolean deleteSupplier(long id);
}
