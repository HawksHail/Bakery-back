package com.cts.capstone.controller;

import com.cts.capstone.bean.*;
import com.cts.capstone.builder.*;
import com.cts.capstone.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ControllerTest {

	private final static Gson gson = new Gson();

	@Mock
	DbService service;

	Controller controller;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		controller = new Controller(service);
	}

	@Test
	public void getCategory() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(service.getCategory(anyLong()))
				.thenReturn(expected);

		String json = controller.getCategory(1L);
		Category actual = gson.fromJson(json, Category.class);

		assertEquals("{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getCategory_notFound() {
		when(service.getCategory(anyLong()))
				.thenReturn(null);

		String json = controller.getCategory(1L);
		Category actual = gson.fromJson(json, Category.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getCategoryList() {
		List<Category> list = new CategoryBuilder()
				.w(1, "name", "description")
				.w(2, "name2", "description2")
				.build();
		when(service.getAllCategories())
				.thenReturn(list);

		String json = controller.getCategory();
		Category[] actual = gson.fromJson(json, Category[].class);

		assertEquals("[" +
						"{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}," +
						"{\"categoryId\":2,\"categoryName\":\"name2\",\"description\":\"description2\"}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	public void getCategoryList_none() {
		when(service.getAllCategories())
				.thenReturn(List.of());

		String json = controller.getCategory();
		Category[] actual = gson.fromJson(json, Category[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	public void getCustomer() {
		Customer expected = CustomerBuilder.of("id123", "companyName", "contactName", "street", "city", "state");
		when(service.getCustomer(anyString()))
				.thenReturn(expected);

		String json = controller.getCustomer("id123");
		Customer actual = gson.fromJson(json, Customer.class);

		assertEquals("{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getCustomer_notFound() {
		when(service.getCustomer(anyString()))
				.thenReturn(null);

		String json = controller.getCustomer("id123");
		Customer actual = gson.fromJson(json, Customer.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getCustomerList() {
		List<Customer> list = new CustomerBuilder()
				.w("id123", "companyName", "contactName", "street", "city", "state")
				.w("id124", "companyName2", "contactName2", "street2", "city2", "state2")
				.build();
		when(service.getAllCustomers())
				.thenReturn(list);

		String json = controller.getCustomer();
		Customer[] actual = gson.fromJson(json, Customer[].class);


		assertEquals("[" +
						"{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}," +
						"{\"customerId\":\"id124\",\"companyName\":\"companyName2\",\"contactName\":\"contactName2\",\"street\":\"street2\",\"city\":\"city2\",\"state\":\"state2\"}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	public void getCustomerList_none() {
		when(service.getAllCustomers())
				.thenReturn(List.of());

		String json = controller.getCustomer();
		Customer[] actual = gson.fromJson(json, Customer[].class);


		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	public void getOrderDetails() {
		OrderDetails expected = OrderDetailsBuilder.of(1, 2, 3);
		when(service.getOrderDetails(anyLong()))
				.thenReturn(expected);

		String json = controller.getOrderDetails(1L);
		OrderDetails actual = gson.fromJson(json, OrderDetails.class);


		assertEquals("{\"orderId\":1,\"productId\":2,\"quantity\":3}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getOrderDetails_notFound() {
		when(service.getOrderDetails(anyLong()))
				.thenReturn(null);

		String json = controller.getOrderDetails(1L);
		OrderDetails actual = gson.fromJson(json, OrderDetails.class);


		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getOrderDetailsList() {
		List<OrderDetails> list = new OrderDetailsBuilder()
				.w(1, 2, 3)
				.w(4, 5, 6)
				.build();
		when(service.getAllOrderDetails())
				.thenReturn(list);

		String json = controller.getOrderDetails();
		OrderDetails[] actual = gson.fromJson(json, OrderDetails[].class);


		assertEquals("[" +
						"{\"orderId\":1,\"productId\":2,\"quantity\":3}," +
						"{\"orderId\":4,\"productId\":5,\"quantity\":6}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	public void getOrderDetailsList_none() {
		when(service.getAllOrderDetails())
				.thenReturn(List.of());

		String json = controller.getOrderDetails();
		OrderDetails[] actual = gson.fromJson(json, OrderDetails[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	public void getOrder() {
		Order expected = OrderBuilder.of(1, "id123", 2021, 9, 1);
		when(service.getOrder(anyLong()))
				.thenReturn(expected);

		String json = controller.getOrder(1L);
		Order actual = gson.fromJson(json, Order.class);

		assertEquals("{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getOrder_notFound() {
		when(service.getOrder(anyLong()))
				.thenReturn(null);

		String json = controller.getOrder(1L);
		Order actual = gson.fromJson(json, Order.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getOrderList() {
		List<Order> list = new OrderBuilder()
				.w(1, "id123", 2021, 9, 1)
				.w(2, "id124", 2020, 10, 2)
				.build();
		when(service.getAllOrders())
				.thenReturn(list);

		String json = controller.getOrder();
		Order[] actual = gson.fromJson(json, Order[].class);

		assertEquals("[" +
						"{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}," +
						"{\"orderId\":2,\"customerId\":\"id124\",\"orderDate\":{\"year\":2020,\"month\":10,\"day\":2}}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	public void getOrderList_none() {
		when(service.getAllOrders())
				.thenReturn(List.of());

		String json = controller.getOrder();
		Order[] actual = gson.fromJson(json, Order[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));

	}

	@Test
	public void getProduct() {
		Product expected = ProductBuilder.of(1, "productName", 2, 3, "4");
		when(service.getProduct(anyLong()))
				.thenReturn(expected);

		String json = controller.getProduct(1L);
		Product actual = gson.fromJson(json, Product.class);

		assertEquals("{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getProduct_notFound() {
		when(service.getProduct(anyLong()))
				.thenReturn(null);

		String json = controller.getProduct(1L);
		Product actual = gson.fromJson(json, Product.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getProductList() {
		List<Product> list = new ProductBuilder()
				.w(1, "productName", 2, 3, "4")
				.w(5, "productName2", 6, 7, "8")
				.build();
		when(service.getAllProducts())
				.thenReturn(list);

		String json = controller.getProduct();
		Product[] actual = gson.fromJson(json, Product[].class);

		assertEquals("[" +
						"{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}," +
						"{\"productId\":5,\"productName\":\"productName2\",\"supplierId\":6,\"categoryId\":7,\"unitPrice\":8}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	public void getProductList_none() {
		when(service.getAllProducts())
				.thenReturn(List.of());

		String json = controller.getProduct();
		Product[] actual = gson.fromJson(json, Product[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	public void getSupplier() {
		Supplier expected = SupplierBuilder.of(1, "companyName", "contactName");
		when(service.getSupplier(anyLong()))
				.thenReturn(expected);

		String json = controller.getSupplier(1L);
		Supplier actual = gson.fromJson(json, Supplier.class);

		assertEquals("{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	public void getSupplier_notFound() {
		when(service.getSupplier(anyLong()))
				.thenReturn(null);

		String json = controller.getSupplier(1L);
		Supplier actual = gson.fromJson(json, Supplier.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	public void getSupplierList() {
		List<Supplier> expected = new SupplierBuilder()
				.w(1, "companyName", "contactName")
				.w(2, "companyName2", "contactName2")
				.build();
		when(service.getAllSuppliers())
				.thenReturn(expected);

		String json = controller.getSupplier();
		Supplier[] actual = gson.fromJson(json, Supplier[].class);

		assertEquals("[" +
						"{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}," +
						"{\"supplierId\":2,\"companyName\":\"companyName2\",\"contactName\":\"contactName2\"}" +
						"]"
				, json);
		assertEquals(expected, List.of(actual));
	}

	@Test
	public void getSupplierList_none() {
		when(service.getAllSuppliers())
				.thenReturn(List.of());

		String json = controller.getSupplier();
		Supplier[] actual = gson.fromJson(json, Supplier[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	public void coverage() {
		new Controller();
	}
}