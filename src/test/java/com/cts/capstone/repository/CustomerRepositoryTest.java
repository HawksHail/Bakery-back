package com.cts.capstone.repository;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	CustomerRepository repository;

	Customer customer;

	@BeforeEach
	void setUp() {
		customer = entityManager.persist(CustomerBuilder.of("customer company", "contact name", "street", "city", "state"));
	}

	@Test
	void save() {
		entityManager.clear();
		customer.setCustomerId(null);

		Customer actual = repository.save(customer);

		assertEquals(customer, actual);
	}

	@Test
	void delete() {
		repository.delete(customer);

		assertNull(entityManager.find(Customer.class, customer.getCustomerId()));
	}

	@Test
	void findAll() {
		List<Customer> all = repository.findAll();

		assertEquals(List.of(customer), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.clear();

		List<Customer> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<Customer> find = repository.findById(customer.getCustomerId());

		assertTrue(find.isPresent());
		assertEquals(customer, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<Customer> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}

	@Test
	void findBySub() {
		Optional<Customer> find = repository.findBySub(customer.getSub());

		assertTrue(find.isPresent());
		assertEquals(customer, find.get());
	}
}