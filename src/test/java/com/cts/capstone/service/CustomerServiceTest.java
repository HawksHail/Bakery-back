package com.cts.capstone.service;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.model.Customer;
import com.cts.capstone.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CustomerServiceTest {

	@Mock
	CustomerRepository repository;

	CustomerService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new CustomerService(repository);
	}

	@Test
	void findAll() {
		List<Customer> expected = new CustomerBuilder()
				.w("id123", "name", "description", "street", "city", "state")
				.w("id124", "name2", "description2", "street2", "city2", "state2")
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<Customer> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		Customer expected = CustomerBuilder.of("id123", "name", "description", "street", "city", "state");
		when(repository.findByCustomerId(anyString()))
				.thenReturn(java.util.Optional.of(expected));

		Customer actual = service.findById("id123");

		assertEquals(expected, actual);
		verify(repository, times(1)).findByCustomerId(anyString());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findByCustomerId(anyString()))
				.thenReturn(java.util.Optional.empty());

		Customer actual = service.findById("id123");

		assertNull(actual);
		verify(repository, times(1)).findByCustomerId(anyString());
	}

	@Test
	void add() {
		Customer expected = CustomerBuilder.of("id123", "name", "description", "street", "city", "state");
		when(repository.save(any(Customer.class)))
				.thenReturn(expected);

		Customer actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Customer.class));
	}

	@Test
	void deleteByCustomerId() {
		Customer customer = CustomerBuilder.of("id123", "name", "description", "street", "city", "state");
		when(repository.findByCustomerId(anyString()))
				.thenReturn(Optional.of(customer));

		boolean delete = service.delete("id123");

		assertTrue(delete);
		verify(repository, times(1)).deleteByCustomerId(anyString());
	}

	@Test
	void deleteByCustomerIdNotFound() {
		when(repository.findByCustomerId(anyString()))
				.thenReturn(Optional.empty());

		boolean delete = service.delete("id123");

		assertFalse(delete);
		verify(repository, times(0)).deleteByCustomerId(anyString());
	}
}