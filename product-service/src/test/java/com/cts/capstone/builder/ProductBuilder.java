package com.cts.capstone.builder;

import com.cts.capstone.model.Category;
import com.cts.capstone.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

	List<Product> list;

	public ProductBuilder() {
		list = new ArrayList<>();
	}

	public static Product of() {
		return new Product();
	}

	public ProductBuilder w(long id, String name, long supplierId, Category category, String unitPrice) {
		list.add(of(id, name, supplierId, category, unitPrice));
		return this;
	}

	public static Product of(long id, String name, long supplierId, Category category, String unitPrice) {
		return new Product(id, name, supplierId, category, new BigDecimal(unitPrice));
	}

	public List<Product> build() {
		return this.list;
	}
}
