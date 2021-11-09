package com.cts.capstone.controller;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.model.Customer;
import com.cts.capstone.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {

	@Mock
	CustomerService service;

	CustomerController controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new CustomerController(service);
	}

	@Test
	void setCustomerService() {
		controller.setCustomerService(null);
		assertNull(controller.getCustomerService());
	}

	@Test
	void getAllCustomers() {
		List<Customer> expected = new CustomerBuilder()
				.w(1234L, "name", "description", "street", "city", "state")
				.w(12345L, "name2", "description2", "street2", "city2", "state2")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Customer> actual = controller.getAllCustomers();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getCustomerBySub() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.findBySub(anyString()))
				.thenReturn(expected);

		Customer actual = controller.getCustomerBySub("ID");

		assertEquals(expected, actual);
		verify(service).findBySub(anyString());
	}

	@Test
	void getCustomerBySubNotFound() {
		when(service.findBySub(anyString()))
				.thenReturn(null);

		assertThrows(CustomerNotFoundException.class, () -> controller.getCustomerBySub("ID"));

		verify(service).findBySub(anyString());
	}

	@Test
	void getCustomer() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.findById(anyLong()))
				.thenReturn(expected);

		Customer actual = controller.getCustomer(1234L);

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void getCustomerNotFound() {
		when(service.findById(anyLong()))
				.thenReturn(null);

		assertThrows(CustomerNotFoundException.class, () -> controller.getCustomer(1234L));

		verify(service, times(1)).findById(anyLong());
	}

	@Test
	void addCustomer() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.add(any(Customer.class)))
				.thenReturn(expected);

		ResponseEntity<Customer> actual = controller.addCustomer(expected);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
		assertTrue(Objects.requireNonNull(actual.getHeaders().get("Location")).get(0).contains(String.valueOf(expected.getCustomerId())));
		verify(service, times(1)).add(any(Customer.class));
	}

	@Test
	void putCustomer() {
		Customer expected = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.add(any(Customer.class)))
				.thenReturn(expected);

		ResponseEntity<Customer> actual = controller.putCustomer(expected);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).add(any(Customer.class));
	}

	@Test
	void deleteCustomerById() {
		Customer customer = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.delete(anyLong()))
				.thenReturn(true);

		ResponseEntity<Customer> actual = controller.deleteCustomerById(1234L);

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(service, times(1)).delete(anyLong());
	}

	@Test
	void deleteCustomerByIdNotFound() {
		Customer customer = CustomerBuilder.of(1234L, "name", "description", "street", "city", "state");
		when(service.delete(anyLong()))
				.thenReturn(false);

		ResponseEntity<Customer> actual = controller.deleteCustomerById(1234L);

		assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
		verify(service, times(1)).delete(anyLong());
	}
}