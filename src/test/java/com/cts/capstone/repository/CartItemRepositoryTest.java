package com.cts.capstone.repository;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartItemRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	CartItemRepository repository;

	Supplier supplier;
	Category category;
	Product product;
	Customer customer;
	CartItem item;

	@BeforeEach
	void setUp() {
		supplier = entityManager.persist(SupplierBuilder.of("company name", "contact name"));
		category = entityManager.persist(CategoryBuilder.of("category name", "description"));
		customer = entityManager.persist(CustomerBuilder.of("customer company", "contact name", "street", "city", "state"));
		product = entityManager.persist(ProductBuilder.of("product name", supplier, category, "765"));
		item = entityManager.persist(new CartItem(customer, product, 1));
		entityManager.flush();
	}

	@Test
	void save() {
		entityManager.remove(item);
		item.setCartItemId(null);

		CartItem actual = repository.save(item);

		assertEquals(item, actual);
	}

	@Test
	void delete() {
		repository.delete(item);

		assertNull(entityManager.find(CartItem.class, item.getCartItemId()));
	}

	@Test
	void findAll() {
		List<CartItem> all = repository.findAll();

		assertEquals(List.of(item), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.remove(item);

		List<CartItem> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<CartItem> find = repository.findById(item.getCartItemId());

		assertTrue(find.isPresent());
		assertEquals(item, find.get());
	}

	@Test
	void findById_NotFound() {
		entityManager.remove(item);

		Optional<CartItem> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}

	@Test
	void findAllByCustomerCustomerId() {
		List<CartItem> all = repository.findAllByCustomerCustomerId(customer.getCustomerId());

		assertEquals(List.of(item), all);
	}

	@Test
	void findAllByCustomerCustomerId_Empty() {
		entityManager.remove(item);

		List<CartItem> all = repository.findAllByCustomerCustomerId(customer.getCustomerId());

		assertEquals(List.of(), all);
	}

	@Test
	void findByCustomerCustomerIdAndProductId() {
		Optional<CartItem> find = repository.findByCustomerCustomerIdAndProductId(customer.getCustomerId(), product.getId());

		assertTrue(find.isPresent());
		assertEquals(item, find.get());
	}

	@Test
	void findByCustomerCustomerIdAndProductId_NotFound() {
		entityManager.remove(item);

		Optional<CartItem> find = repository.findByCustomerCustomerIdAndProductId(customer.getCustomerId(), product.getId());

		assertFalse(find.isPresent());
	}
}