package com.cts.capstone.repository;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.model.Category;
import com.cts.capstone.model.Product;
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
class ProductRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ProductRepository repository;

	Product product;
	Supplier supplier;
	Category category;

	@BeforeEach
	void setUp() {
		supplier = entityManager.persist(SupplierBuilder.of("company name", "contact name"));
		category = entityManager.persist(CategoryBuilder.of("category name", "description"));
		product = entityManager.persist(ProductBuilder.of("product name", supplier, category, "765"));
		entityManager.flush();
	}

	@Test
	void save() {
		entityManager.remove(product);
		entityManager.flush();

		product.setId(null);

		Product actual = repository.save(product);

		assertEquals(product, actual);
	}

	@Test
	void delete() {
		repository.delete(product);

		assertNull(entityManager.find(Product.class, product.getId()));
	}

	@Test
	void findAll() {
		List<Product> all = repository.findAll();

		assertEquals(List.of(product), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.remove(product);
		entityManager.flush();

		List<Product> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<Product> find = repository.findById(product.getId());

		assertTrue(find.isPresent());
		assertEquals(product, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<Product> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}

	@Test
	void findAllByOrderByProductNameAsc() {
		List<Product> all = repository.findAllByOrderByProductNameAsc();

		assertEquals(List.of(product), all);
	}
}