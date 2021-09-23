package com.cts.capstone.bean;

import com.cts.capstone.builder.OrderDetailsBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderDetailsTest {

	OrderDetails orderDetails;

	@BeforeEach
	void setUp() {
		orderDetails = OrderDetailsBuilder.of(123L, 456L, 100);
	}

	@Test
	void setOrderId() {
		orderDetails.setOrderId(987L);
		assertEquals(987L, orderDetails.getOrderId());
	}

	@Test
	void setProductId() {
		orderDetails.setProductId(987L);
		assertEquals(987L, orderDetails.getProductId());
	}

	@Test
	void setQuantity() {
		orderDetails.setQuantity(987);
		assertEquals(987, orderDetails.getQuantity());
	}
}