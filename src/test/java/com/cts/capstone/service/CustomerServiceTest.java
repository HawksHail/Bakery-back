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
				.w(1234L, "name", "description", "street", "city", "state")
				.w(1235L, "name2", "description2", "street2", "city2", "state2")
				.build();
		when(repository.findAll())
				.thenReturn(expected);

		List<Customer> actual = service.findAll();

		assertEquals(expected, actual);
		verify(repository, times(1)).findAll();
	}

	@Test
	void findById() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.of(expected));

		Customer actual = service.findById(1234L);

		assertEquals(expected, actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(java.util.Optional.empty());

		Customer actual = service.findById(1234L);

		assertNull(actual);
		verify(repository, times(1)).findById(anyLong());
	}

	@Test
	void add() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(repository.save(any(Customer.class)))
				.thenReturn(expected);

		Customer actual = service.add(expected);

		assertEquals(expected, actual);
		verify(repository, times(1)).save(any(Customer.class));
	}

	@Test
	void deleteByCustomerId() {
		Customer customer = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(repository.findById(anyLong()))
				.thenReturn(Optional.of(customer));

		boolean delete = service.delete(1234L);

		assertTrue(delete);
		verify(repository, times(1)).deleteById(anyLong());
	}

	@Test
	void deleteByCustomerIdNotFound() {
		when(repository.findById(anyLong()))
				.thenReturn(Optional.empty());

		boolean delete = service.delete(1234L);

		assertFalse(delete);
		verify(repository, times(0)).deleteById(anyLong());
	}
}