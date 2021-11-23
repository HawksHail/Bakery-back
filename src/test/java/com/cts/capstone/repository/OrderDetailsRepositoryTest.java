package com.cts.capstone.repository;

import com.cts.capstone.builder.*;
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
class OrderDetailsRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	OrderDetailsRepository repository;

	Customer customer;
	Order order;
	OrderDetails orderDetails;
	Product product;
	Supplier supplier;
	Category category;

	@BeforeEach
	void setUp() {
		customer = entityManager.persist(CustomerBuilder.of("customer company", "contact name", "street", "city", "state"));
		order = entityManager.persist(OrderBuilder.of(customer));
		supplier = entityManager.persist(SupplierBuilder.of("company name", "contact name"));
		category = entityManager.persist(CategoryBuilder.of("category name", "description"));
		product = entityManager.persist(ProductBuilder.of("product name", supplier, category, "765"));
		entityManager.flush();
		OrderDetails details = OrderDetailsBuilder.of(order, product, 2);
		orderDetails = entityManager.persist(details);
	}

	@Test
	void save() {
		entityManager.remove(orderDetails);
		orderDetails = OrderDetailsBuilder.of(order, product, 6);

		OrderDetails actual = repository.save(orderDetails);

		assertEquals(orderDetails, actual);
	}

	@Test
	void delete() {
		repository.delete(orderDetails);

		assertNull(entityManager.find(OrderDetails.class, orderDetails.getId()));
	}

	@Test
	void findAll() {
		List<OrderDetails> all = repository.findAll();

		assertEquals(List.of(orderDetails), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.clear();

		List<OrderDetails> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<OrderDetails> find = repository.findById(orderDetails.getId());

		assertTrue(find.isPresent());
		assertEquals(orderDetails, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<OrderDetails> find = repository.findById(new OrderDetailsKey(123L, 123L));

		assertFalse(find.isPresent());
	}

	@Test
	void findAllByIdOrderId() {
		List<OrderDetails> all = repository.findAllByIdOrderId(order.getId());

		assertEquals(List.of(orderDetails), all);
	}

	@Test
	void findAllByIdOrderId_NotFound() {
		List<OrderDetails> all = repository.findAllByIdOrderId(1234L);

		assertEquals(List.of(), all);
	}
}