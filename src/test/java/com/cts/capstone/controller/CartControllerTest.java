package com.cts.capstone.controller;

import com.cts.capstone.builder.CartItemBuilder;
import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.exception.CustomerNotFoundException;
import com.cts.capstone.exception.ProductNotFoundException;
import com.cts.capstone.model.CartItem;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Order;
import com.cts.capstone.model.Product;
import com.cts.capstone.service.CartItemService;
import com.cts.capstone.service.CustomerService;
import com.cts.capstone.service.OrderService;
import com.cts.capstone.service.ProductService;
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
class CartControllerTest {

	@Mock
	CustomerService customerService;

	@Mock
	ProductService productService;

	@Mock
	CartItemService cartItemService;

	@Mock
	OrderService orderService;

	CartController controller;

	Customer customer;
	Product product;
	Product product2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = CustomerBuilder.of(1234L, "company name", "contact name", "street", "city", "state");
		product = ProductBuilder.of(1, "product name", "123");
		product2 = ProductBuilder.of(2, "product2 name", "321");
		customer.getCart().add(new CartItem(customer, product, 1));

		controller = new CartController();
		controller.setOrderService(orderService);
		controller.setCartItemService(cartItemService);
		controller.setCustomerService(customerService);
		controller.setProductService(productService);
	}

	@Test
	void setCustomerService() {
		controller.setCustomerService(null);
		assertNull(controller.getCustomerService());
	}

	@Test
	void setProductService() {
		controller.setProductService(null);
		assertNull(controller.getProductService());
	}

	@Test
	void setCartRepository() {
		controller.setCartItemService(null);
		assertNull(controller.getCartItemService());
	}

	@Test
	void setOrderService() {
		controller.setOrderService(null);
		assertNull(controller.getOrderService());
	}

	@Test
	void getCart() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);

		ResponseEntity<List<CartItem>> actual = controller.getCart(customer.getCustomerId());

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertEquals(customer.getCart(), actual.getBody());
		verify(customerService, times(1)).findById(anyLong());
	}

	@Test
	void getCartNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(null);

		assertThrows(CustomerNotFoundException.class, () -> controller.getCart(customer.getCustomerId()));

		verify(customerService, times(1)).findById(anyLong());
	}

	@Test
	void addCartProduct_newItem() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(product2);

		ResponseEntity<List<CartItem>> actual = controller.addCartProduct(customer.getCustomerId(), product2.getId(), 1);

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		verify(customerService).findById(anyLong());
		verify(productService).findById(anyLong());
		verify(cartItemService).add(any(Customer.class), any(Product.class), anyInt());
		verify(cartItemService).findAllByCustomerId(anyLong());
	}

	@Test
	void addCartProductCustomerNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(null);
		when(productService.findById(anyLong()))
				.thenReturn(product2);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.addCartProduct(customer.getCustomerId(), product2.getId(), 1));

		verify(customerService, times(1)).findById(anyLong());
		verify(productService, times(0)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void addCartProductProductNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(ProductNotFoundException.class, () -> controller.addCartProduct(customer.getCustomerId(), product2.getId(), 1));

		verify(customerService, times(1)).findById(anyLong());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartProductRemoveItem() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(product);

		ResponseEntity<List<CartItem>> actual = controller.deleteCartProduct(customer.getCustomerId(), product2.getId(), 1);

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		verify(customerService).findById(anyLong());
		verify(productService).findById(anyLong());
		verify(cartItemService).remove(any(Customer.class), any(Product.class), anyInt());
		verify(cartItemService).findAllByCustomerId(anyLong());
	}

	@Test
	void deleteCartProductCustomerNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(null);
		when(productService.findById(anyLong()))
				.thenReturn(product);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.deleteCartProduct(customer.getCustomerId(), product2.getId(), 1));

		verify(customerService, times(1)).findById(anyLong());
		verify(productService, times(0)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartProductProductNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(productService.findById(anyLong()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(ProductNotFoundException.class, () -> controller.deleteCartProduct(customer.getCustomerId(), product2.getId(), 1));

		verify(customerService, times(1)).findById(anyLong());
		verify(productService, times(1)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void deleteCartAllProduct() {
		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		ResponseEntity<List<CartItem>> actual = controller.deleteCartAllProduct(customer.getCustomerId());

		assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
		verify(customerService, times(1)).findById(anyLong());
		verify(cartItemService, times(1)).clear(anyLong());
	}

	@Test
	void deleteCartAllProductCustomerNotFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(null);
		when(customerService.add(any(Customer.class)))
				.thenReturn(customer);

		assertThrows(CustomerNotFoundException.class, () -> controller.deleteCartAllProduct(customer.getCustomerId()));

		verify(customerService, times(1)).findById(anyLong());
		verify(customerService, times(0)).add(any(Customer.class));
	}

	@Test
	void checkoutCart() {
		List<CartItem> cart = new CartItemBuilder(customer)
				.w(product, 1)
				.w(product2, 2)
				.build();

		when(customerService.findById(anyLong()))
				.thenReturn(customer);
		when(cartItemService.findAllByCustomerId(anyLong()))
				.thenReturn(cart);
		when(orderService.add(any(Order.class)))
				.then(a -> {
					a.getArgument(0, Order.class).setId(56L);
					return a.getArgument(0, Order.class);
				});

		ResponseEntity<Object> actual = controller.checkoutCart(customer.getCustomerId());

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
		assertTrue(Objects.requireNonNull(actual.getHeaders().getLocation()).toString().contains("/order/56"));
		verify(customerService).findById(anyLong());
		verify(cartItemService).findAllByCustomerId(anyLong());
		verify(orderService, times(2)).add(any(Order.class));
		verify(cartItemService).clear(anyLong());
	}

	@Test
	void checkoutCart_notFound() {
		when(customerService.findById(anyLong()))
				.thenReturn(null);

		assertThrows(CustomerNotFoundException.class, () -> controller.checkoutCart(customer.getCustomerId()));

		verify(customerService, times(1)).findById(anyLong());
	}


}