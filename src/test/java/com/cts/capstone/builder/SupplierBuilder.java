package com.cts.capstone.builder;

import com.cts.capstone.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierBuilder {

	List<Supplier> list;

	public SupplierBuilder() {
		list = new ArrayList<>();
	}

	public static Supplier of() {
		return new Supplier();
	}

	public SupplierBuilder w(long id, String companyName, String contactName) {
		list.add(of(id, companyName, contactName));
		return this;
	}

	public static Supplier of(long id, String companyName, String contactName) {
		return new Supplier(id, companyName, contactName);
	}

	public static Supplier of(String companyName, String contactName) {
		Supplier supplier = new Supplier();
		supplier.setCompanyName(companyName);
		supplier.setContactName(contactName);
		return supplier;
	}

	public List<Supplier> build() {
		return this.list;
	}
}
