package com.cts.capstone.builder;

import com.cts.capstone.model.Category;
import com.cts.capstone.model.Product;
import com.cts.capstone.model.Supplier;

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

	public static Product of(int id, String name, String unitPrice) {
		return of(id, name, new Supplier(), new Category(), unitPrice);
	}

	public ProductBuilder w(long id, String name, Supplier supplier, Category category, String unitPrice) {
		list.add(of(id, name, supplier, category, unitPrice));
		return this;
	}

	public static Product of(long id, String name, Supplier supplier, Category category, String unitPrice) {
		return new Product(id, name, supplier, category, new BigDecimal(unitPrice));
	}

	public static Product of(String name, Supplier supplier, Category category, String unitPrice) {
		Product product = new Product();
		product.setProductName(name);
		product.setSupplier(supplier);
		product.setCategory(category);
		product.setUnitPrice(unitPrice);
		return product;
	}

	public List<Product> build() {
		return this.list;
	}

	public ProductBuilder w(int id, String name, String unitPrice) {
		list.add(of(id, name, new Supplier(), new Category(), unitPrice));
		return this;
	}
}
