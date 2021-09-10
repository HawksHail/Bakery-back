package com.cts.capstone.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

	Supplier supplier;

	@BeforeEach
	void setUp() {
		supplier = new Supplier(123L, "company", "contact");
	}

	@Test
	void setSupplierId() {
		supplier.setSupplierId(987L);
		assertEquals(987L, supplier.getSupplierId());
	}

	@Test
	void setCompanyName() {
		supplier.setCompanyName("abc");
		assertEquals("abc", supplier.getCompanyName());
	}

	@Test
	void setContactName() {
		supplier.setContactName("abc");
		assertEquals("abc", supplier.getContactName());
	}
}