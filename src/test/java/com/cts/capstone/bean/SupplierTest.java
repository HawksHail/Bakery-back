package com.cts.capstone.bean;

import com.cts.capstone.builder.SupplierBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SupplierTest {

	Supplier supplier;

	@BeforeEach
	void setUp() {
		supplier = SupplierBuilder.of(123L, "company", "contact");
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