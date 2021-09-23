package com.cts.capstone.builder;

import com.cts.capstone.bean.Supplier;

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

	public List<Supplier> build() {
		return this.list;
	}
}
