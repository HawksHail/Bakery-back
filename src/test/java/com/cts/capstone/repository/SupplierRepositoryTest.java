package com.cts.capstone.repository;

import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SupplierRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	SupplierRepository repository;

	Supplier supplier;

	@BeforeEach
	void setUp() {
		supplier = entityManager.persist(SupplierBuilder.of("company name", "contact name"));
	}

	@Test
	void save() {
		entityManager.clear();
		supplier.setId(0);

		Supplier actual = repository.save(supplier);

		assertEquals(supplier, actual);
	}

	@Test
	void delete() {
		repository.delete(supplier);

		assertNull(entityManager.find(Supplier.class, supplier.getId()));
	}

	@Test
	void findAll() {
		List<Supplier> all = repository.findAll();

		assertEquals(List.of(supplier), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.clear();

		List<Supplier> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<Supplier> find = repository.findById(supplier.getId());

		assertTrue(find.isPresent());
		assertEquals(supplier, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<Supplier> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}
}