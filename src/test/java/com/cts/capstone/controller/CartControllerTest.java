package com.cts.capstone.controller;


import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.OrderBuilder;
import com.cts.capstone.builder.OrderDetailsBuilder;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Order;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


class CartControllerTest {

	private final static Gson gson = new Gson();

	@Mock
	DbService service;

	CartController cartController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		cartController = new CartController(service);
	}


	@Test
	void getCustomer() {
		Customer expected = CustomerBuilder.of("id123", "companyName", "contactName", "street", "city", "state");
		when(service.getCustomer(anyString()))
				.thenReturn(expected);

		String json = cartController.getCustomer("id123");
		Customer actual = gson.fromJson(json, Customer.class);

		assertEquals("{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getCustomer_notFound() {
		when(service.getCustomer(anyString()))
				.thenReturn(null);

		String json = cartController.getCustomer("id123");
		Customer actual = gson.fromJson(json, Customer.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getCustomerList() {
		List<Customer> list = new CustomerBuilder()
				.w("id123", "companyName", "contactName", "street", "city", "state")
				.w("id124", "companyName2", "contactName2", "street2", "city2", "state2")
				.build();
		when(service.getAllCustomers())
				.thenReturn(list);

		String json = cartController.getCustomer();
		Customer[] actual = gson.fromJson(json, Customer[].class);

		assertEquals("[" +
						"{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}," +
						"{\"customerId\":\"id124\",\"companyName\":\"companyName2\",\"contactName\":\"contactName2\",\"street\":\"street2\",\"city\":\"city2\",\"state\":\"state2\"}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	void getCustomerList_none() {
		when(service.getAllCustomers())
				.thenReturn(List.of());

		String json = cartController.getCustomer();
		Customer[] actual = gson.fromJson(json, Customer[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void getOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.getOrderDetails(anyLong()))
				.thenReturn(expected);

		String json = cartController.getOrderDetails(1L);
		OrderDetails actual = gson.fromJson(json, OrderDetails.class);

		assertEquals("{\"orderId\":1,\"productId\":2,\"quantity\":3}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getOrderDetails_notFound() {
		when(service.getOrderDetails(anyLong()))
				.thenReturn(null);

		String json = cartController.getOrderDetails(1L);
		OrderDetails actual = gson.fromJson(json, OrderDetails.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getOrderDetailsList() {
		List<OrderDetails> list = new OrderDetailsBuilder()
				.w(1, 2, 3)
				.w(4, 5, 6)
				.build();
		when(service.getAllOrderDetails())
				.thenReturn(list);

		String json = cartController.getOrderDetails();
		OrderDetails[] actual = gson.fromJson(json, OrderDetails[].class);

		assertEquals("[" +
						"{\"orderId\":1,\"productId\":2,\"quantity\":3}," +
						"{\"orderId\":4,\"productId\":5,\"quantity\":6}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	void getOrderDetailsList_none() {
		when(service.getAllOrderDetails())
				.thenReturn(List.of());

		String json = cartController.getOrderDetails();
		OrderDetails[] actual = gson.fromJson(json, OrderDetails[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void getOrder() {
		Order expected = OrderBuilder.of(1, "id123", 2021, 9, 1);
		when(service.getOrder(anyLong()))
				.thenReturn(expected);

		String json = cartController.getOrder(1L);
		Order actual = gson.fromJson(json, Order.class);

		assertEquals("{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getOrder_notFound() {
		when(service.getOrder(anyLong()))
				.thenReturn(null);

		String json = cartController.getOrder(1L);
		Order actual = gson.fromJson(json, Order.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getOrderList() {
		List<Order> list = new OrderBuilder()
				.w(1, "id123", 2021, 9, 1)
				.w(2, "id124", 2020, 10, 2)
				.build();
		when(service.getAllOrders())
				.thenReturn(list);

		String json = cartController.getOrder();
		Order[] actual = gson.fromJson(json, Order[].class);

		assertEquals("[" +
						"{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}," +
						"{\"orderId\":2,\"customerId\":\"id124\",\"orderDate\":{\"year\":2020,\"month\":10,\"day\":2}}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	void getOrderList_none() {
		when(service.getAllOrders())
				.thenReturn(List.of());

		String json = cartController.getOrder();
		Order[] actual = gson.fromJson(json, Order[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void createOrder() {
		Order expected = OrderBuilder.of(1, "id123", 2021, 9, 1);
		when(service.createOrder(any(Order.class)))
				.thenReturn(true);

		Order actual = cartController.createOrder(expected);

		assertEquals(expected, actual);
	}

	@Test
	void createOrder_NoDate() {
		Order expected = OrderBuilder.of(1, "id123");
		when(service.createOrder(any(Order.class)))
				.thenReturn(true);

		Order actual = cartController.createOrder(expected);

		expected.setOrderDate(LocalDate.now());
		assertEquals(expected, actual);
	}

	@Test
	void createOrder_AlreadyExists() {
		Order expected = OrderBuilder.of(1, "id123");
		when(service.createOrder(any(Order.class)))
				.thenReturn(false);

		Order actual = cartController.createOrder(expected);

		assertNull(actual);
	}

	@Test
	void updateOrder() {
		Order expected = OrderBuilder.of(1, "id123", 2021, 9, 1);
		when(service.updateOrder(any(Order.class)))
				.thenReturn(true);

		Order actual = cartController.updateOrder(expected);

		assertEquals(expected, actual);
	}

	@Test
	void updateOrder_failed() {
		Order expected = OrderBuilder.of(1, "id123", 2021, 9, 1);
		when(service.updateOrder(any(Order.class)))
				.thenReturn(false);

		Order actual = cartController.createOrder(expected);

		assertNull(actual);
	}

	@Test
	void createOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.createOrderDetails(any(OrderDetails.class)))
				.thenReturn(true);

		OrderDetails actual = cartController.createOrderDetails(expected);

		assertEquals(expected, actual);
	}

	@Test
	void createOrderDetails_AlreadyExists() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.createOrderDetails(any(OrderDetails.class)))
				.thenReturn(false);

		OrderDetails actual = cartController.createOrderDetails(expected);

		assertNull(actual);
	}

	@Test
	void updateOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.updateOrderDetails(any(OrderDetails.class)))
				.thenReturn(true);

		OrderDetails actual = cartController.updateOrderDetails(expected);

		assertEquals(expected, actual);
	}

	@Test
	void updateOrderDetails_failed() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.updateOrderDetails(any(OrderDetails.class)))
				.thenReturn(false);

		OrderDetails actual = cartController.createOrderDetails(expected);

		assertNull(actual);
	}

	@Test
	void getUserOrders() {
		List<Order> expected = new OrderBuilder()
				.w(1, "id123", 2021, 9, 1)
				.w(2, "id123", 2020, 10, 2)
				.build();
		when(service.getOrdersForCustomer(anyString()))
				.thenReturn(expected);

		String json = cartController.getUserOrders("id123");
		Order[] actual = gson.fromJson(json, Order[].class);

		assertEquals("[" +
						"{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}," +
						"{\"orderId\":2,\"customerId\":\"id123\",\"orderDate\":{\"year\":2020,\"month\":10,\"day\":2}}" +
						"]"
				, json);
		assertEquals(expected, List.of(actual));
	}

	@Test
	void getOrderDetailsForOrder() {
		List<OrderDetails> list = new OrderDetailsBuilder()
				.w(1, 2, 3)
				.w(1, 5, 6)
				.build();
		when(service.getDetailsForOrder(anyLong()))
				.thenReturn(list);

		String json = cartController.getOrderDetailsForOrder(1L);
		OrderDetails[] actual = gson.fromJson(json, OrderDetails[].class);

		assertEquals("[" +
						"{\"orderId\":1,\"productId\":2,\"quantity\":3}," +
						"{\"orderId\":1,\"productId\":5,\"quantity\":6}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}
}