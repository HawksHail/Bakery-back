package com.cts.capstone.controller;

import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.Cart;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CartControllerTest {

	@Mock
	CustomerService customerService;

	@Mock
	ProductService productService;

	CartController controller;

	Customer customer;
	Product product;
	Product product2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = CustomerBuilder.of("test1", "company name", "contact name", "street", "city", "state");
		product = ProductBuilder.of(1, "product name", "123");
		product2 = ProductBuilder.of(2, "product2 name", "321");
		customer.getCart().add(product);
		controller = new CartController(customerService, productService);
	}

	@Test
	void getCart() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);

		ResponseEntity<Object> actual = controller.getCart(customer.getCustomerId());

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertEquals(customer.getCart(), actual.getBody());
		verify(customerService, times(1)).findById(anyString());
	}

	@Test
	void getCartNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(null);

		assertThrows(CustomerNotFoundException.class, () -> controller.getCart(customer.getCustomerId()));

		verify(customerService, times(1)).findById(anyString());
	}

	@Test
	void addCartProduct() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(product2);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		ResponseEntity<Object> actual = controller.addCartProduct(customer.getCustomerId(), product2.getId());

		Cart body = (Cart) actual.getBody();

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertNotNull(body);
		assertEquals(2, body.getItems().size());
		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(1)).add(any(Customer.class));
	}

	@Test
	void addCartProductCustomerNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(null);
		when(productService.findById(anyLong()))
				.thenReturn(product2);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.addCartProduct(customer.getCustomerId(), product2.getId()));

		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(0)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void addCartProductProductNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(ProductNotFoundException.class, () -> controller.addCartProduct(customer.getCustomerId(), product2.getId()));

		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartProduct() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(product);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		ResponseEntity<Object> actual = controller.deleteCartProduct(customer.getCustomerId(), product2.getId());

		Cart body = (Cart) actual.getBody();

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertNotNull(body);
		assertEquals(0, body.getItems().size());
		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(1)).add(any(Customer.class));
	}

	@Test
	void deleteCartProductCustomerNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(null);
		when(productService.findById(anyLong()))
				.thenReturn(product);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.deleteCartProduct(customer.getCustomerId(), product2.getId()));

		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(0)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartProductProductNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(ProductNotFoundException.class, () -> controller.deleteCartProduct(customer.getCustomerId(), product2.getId()));

		verify(customerService, times(1)).findById(anyString());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartAllProduct() {
		when(customerService.findById(anyString()))
				.thenReturn(customer);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		ResponseEntity<Object> actual = controller.deleteCartAllProduct(customer.getCustomerId());

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(customerService, times(1)).findById(anyString());
		verify(customerService, times(1)).add(any(Customer.class));
	}

	@Test
	void deleteCartAllProductCustomerNotFound() {
		when(customerService.findById(anyString()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.deleteCartAllProduct(customer.getCustomerId()));

		verify(customerService, times(1)).findById(anyString());
		verify(customerService, times(0)).add(any(Customer.class));
	}
}