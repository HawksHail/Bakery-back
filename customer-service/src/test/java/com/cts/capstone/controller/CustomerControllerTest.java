package com.cts.capstone.controller;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.model.Customer;
import com.cts.capstone.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void getAllCategories() {
		List<Customer> expected = new CustomerBuilder()
				.w("id123", "name", "description", "street", "city", "state")
				.w("id124", "name2", "description2", "street2", "city2", "state2")
				.build();
		when(service.findAll())
				.thenReturn(expected);

		List<Customer> actual = controller.getAllCustomers();

		assertEquals(expected, actual);
		verify(service, times(1)).findAll();
	}

	@Test
	void getCustomer() {
		Customer expected = CustomerBuilder.of("id123", "name", "description", "street", "city", "state");
		when(service.findById(anyString()))
				.thenReturn(expected);

		Customer actual = controller.getCustomer("id123");

		assertEquals(expected, actual);
		verify(service, times(1)).findById(anyString());
	}

	@Test
	void addCustomer() {
		Customer expected = CustomerBuilder.of("id123", "name", "description", "street", "city", "state");
		when(service.add(any(Customer.class)))
				.thenReturn(expected);

		Customer actual = controller.addCustomer(expected).getBody();

		assertEquals(expected, actual);
		verify(service, times(1)).add(any(Customer.class));
	}
}