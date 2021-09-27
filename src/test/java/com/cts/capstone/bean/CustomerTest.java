package com.cts.capstone.bean;

import com.cts.capstone.builder.CustomerBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

	Customer customer;

	@BeforeEach
	void setUp() {
		customer = CustomerBuilder.of("cm1234", "Cognizant", "Bob", "street", "city", "state");
	}

	@Test
	void setCustomerId() {
		customer.setCustomerId("abc");
		assertEquals("abc", customer.getCustomerId());
	}

	@Test
	void setCompanyName() {
		customer.setCompanyName("abc");
		assertEquals("abc", customer.getCompanyName());
	}

	@Test
	void setContactName() {
		customer.setContactName("abc");
		assertEquals("abc", customer.getContactName());
	}

	@Test
	void setStreet() {
		customer.setStreet("abc");
		assertEquals("abc", customer.getStreet());
	}

	@Test
	void setCity() {
		customer.setCity("abc");
		assertEquals("abc", customer.getCity());
	}

	@Test
	void setState() {
		customer.setState("abc");
		assertEquals("abc", customer.getState());
	}

	@Test
	void hashcodeAndEquals() {
		Customer x = CustomerBuilder.of("cm1234", "Cognizant", "Bob", "street", "city", "state");
		Customer y = CustomerBuilder.of("cm1234", "Cognizant", "Bob", "street", "city", "state");
		Customer a = CustomerBuilder.of();
		Customer b = CustomerBuilder.of();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(Customer.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.verify();
	}
}