package com.cts.capstone.model;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartItemTest {

	CartItem cartItem;

	@BeforeEach
	void setUp() {
		cartItem = new CartItem(
				CustomerBuilder.of(1234L, "Cognizant", "Bob", "street", "city", "state"),
				ProductBuilder.of(123L, "name", new Supplier(), new Category(), "765"),
				1);
	}

	@Test
	void CartItem() {
		CartItem cartItem2 = new CartItem(ProductBuilder.of(123L, "name", new Supplier(), new Category(), "765"));
		cartItem2.setCustomer(CustomerBuilder.of(1234L, "Cognizant", "Bob", "street", "city", "state"));
		assertEquals(cartItem, cartItem2);
	}

	@Test
	void setCartItemId() {
		cartItem.setCartItemId(4321L);
		assertEquals(4321, cartItem.getCartItemId());
	}

	@Test
	void setCustomer() {
		Customer customer2 = CustomerBuilder.of(765L, "New Company", "Rob", "street2", "city2", "state2");
		cartItem.setCustomer(customer2);
		assertEquals(customer2, cartItem.getCustomer());
	}

	@Test
	void setProduct() {
		Product product2 = ProductBuilder.of(7654L, "name2", new Supplier(), new Category(), "765432");
		cartItem.setProduct(product2);
		assertEquals(product2, cartItem.getProduct());
	}

	@Test
	void setQuantity() {
		cartItem.setQuantity(2);
		assertEquals(2, cartItem.getQuantity());
	}

	@Test
	void hashcodeAndEquals() {
		CartItem x = new CartItem(
				CustomerBuilder.of(1234L, "Cognizant", "Bob", "street", "city", "state"),
				ProductBuilder.of(123L, "name", new Supplier(), new Category(), "765"),
				1);
		CartItem y = new CartItem(
				CustomerBuilder.of(1234L, "Cognizant", "Bob", "street", "city", "state"),
				ProductBuilder.of(123L, "name", new Supplier(), new Category(), "765"),
				1);
		CartItem a = new CartItem();
		CartItem b = new CartItem();

		assertTrue(x.equals(y) && y.equals(x));
		assertEquals(x.hashCode(), y.hashCode());
		assertTrue(a.equals(b) && b.equals(a));
		assertEquals(a.hashCode(), b.hashCode());
	}

	@Test
	void toStringTest() {
		ToStringVerifier.forClass(CartItem.class)
				.withClassName(NameStyle.SIMPLE_NAME)
				.withIgnoredFields("customer", "product")
				.verify();
	}
}