package com.cts.capstone.repository;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	CategoryRepository repository;

	Category category;

	@BeforeEach
	void setUp() {
		category = entityManager.persistAndFlush(CategoryBuilder.of("category name", "description"));
	}

	@Test
	void save() {
		entityManager.remove(category);
		entityManager.flush();
		category.setId(null);

		Category actual = repository.save(category);

		assertEquals(category, actual);
	}

	@Test
	void delete() {
		repository.delete(category);

		assertNull(entityManager.find(Category.class, category.getId()));
	}

	@Test
	void findAll() {
		List<Category> all = repository.findAll();

		assertEquals(List.of(category), all);
	}

	@Test
	public void findAll_Empty() {
		entityManager.remove(category);
		entityManager.flush();

		List<Category> all = repository.findAll();

		assertEquals(List.of(), all);
	}

	@Test
	void findById() {
		Optional<Category> find = repository.findById(category.getId());

		assertTrue(find.isPresent());
		assertEquals(category, find.get());
	}

	@Test
	void findById_NotFound() {
		Optional<Category> find = repository.findById(1234L);

		assertFalse(find.isPresent());
	}

	@Test
	void findAllByOrderByCategoryNameAsc() {
		List<Category> all = repository.findAllByOrderByCategoryNameAsc();

		assertEquals(List.of(category), all);
	}
}