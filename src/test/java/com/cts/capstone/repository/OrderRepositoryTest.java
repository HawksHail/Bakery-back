package com.cts.capstone.repository;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	OrderRepository repository;

	Customer customer;
	Order order;

	@BeforeEach
	void setUp() {
		customer = entityManager.persist(CustomerBuilder.of("customer company", "contact name", "street", "city", "state"));

		order = entityManager.persist(OrderBuilder.of(customer));
		entityManager.flush();
	}

	@Test
	void save() {
		entityManager.remove(order);
		order = OrderBuilder.of(customer);

		Order actual = repository.save(order);

		assertEquals(order, actual);
	}

	@Test
	void delete() {
		repository.delete(order);

		assertNull(entityManager.find(Order.class, order.getId()));
	}

	@Test
	void findAll() {
		List<Order> all = repository.findAll();

		assertEquals(List.of(order), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.remove(order);

		List<Order> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<Order> find = repository.findById(order.getId());

		assertTrue(find.isPresent());
		assertEquals(order, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<Order> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}

	@Test
	void findByCustomerId() {
		List<Order> all = repository.findByCustomerIdOrderByOrderDateDescIdDesc(customer.getId());

		assertEquals(List.of(order), all);
	}
}