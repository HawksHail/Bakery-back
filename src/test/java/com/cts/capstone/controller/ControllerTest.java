package com.cts.capstone.controller;

import com.cts.capstone.bean.*;
import com.cts.capstone.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ControllerTest {

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
		when(service.getCategory(anyLong()))
				.thenReturn(new Category(1, "name", "description"));

		String actual = controller.getCategory(1L);

		assertEquals("{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}", actual);
	}

	@Test
	public void getCategory_notFound() {
		when(service.getCategory(anyLong()))
				.thenReturn(null);

		String actual = controller.getCategory(1L);

		assertEquals("null", actual);
	}

	@Test
	public void getCategoryList() {
		when(service.getAllCategories())
				.thenReturn(Arrays.asList(
						new Category(1, "name", "description"),
						new Category(2, "name2", "description2")
				));

		String actual = controller.getCategory();

		assertEquals("[" +
						"{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}," +
						"{\"categoryId\":2,\"categoryName\":\"name2\",\"description\":\"description2\"}" +
						"]"
				, actual);
	}

	@Test
	public void getCategoryList_none() {
		when(service.getAllCategories())
				.thenReturn(List.of());

		String actual = controller.getCategory();

		assertEquals("[]", actual);
	}

	@Test
	public void getCustomer() {
		when(service.getCustomer(anyString()))
				.thenReturn(new Customer("id123", "companyName", "contactName", "street", "city", "state"));

		String actual = controller.getCustomer("id123");

		assertEquals("{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}", actual);
	}

	@Test
	public void getCustomer_notFound() {
		when(service.getCustomer(anyString()))
				.thenReturn(null);

		String actual = controller.getCustomer("id123");

		assertEquals("null", actual);
	}

	@Test
	public void getCustomerList() {
		when(service.getAllCustomers())
				.thenReturn(Arrays.asList(
						new Customer("id123", "companyName", "contactName", "street", "city", "state"),
						new Customer("id124", "companyName2", "contactName2", "street2", "city2", "state2")
				));

		String actual = controller.getCustomer();

		assertEquals("[" +
						"{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}," +
						"{\"customerId\":\"id124\",\"companyName\":\"companyName2\",\"contactName\":\"contactName2\",\"street\":\"street2\",\"city\":\"city2\",\"state\":\"state2\"}" +
						"]"
				, actual);
	}

	@Test
	public void getCustomerList_none() {
		when(service.getAllCustomers())
				.thenReturn(List.of());

		String actual = controller.getCustomer();

		assertEquals("[]", actual);
	}

	@Test
	public void getOrderDetails() {
		when(service.getOrderDetails(anyLong()))
				.thenReturn(new OrderDetails(1, 2, 3));

		String actual = controller.getOrderDetails(1L);

		assertEquals("{\"orderId\":1,\"productId\":2,\"quantity\":3}", actual);
	}

	@Test
	public void getOrderDetails_notFound() {
		when(service.getOrderDetails(anyLong()))
				.thenReturn(null);

		String actual = controller.getOrderDetails(1L);

		assertEquals("null", actual);
	}

	@Test
	public void getOrderDetailsList() {
		when(service.getAllOrderDetails())
				.thenReturn(Arrays.asList(
						new OrderDetails(1, 2, 3),
						new OrderDetails(4, 5, 6)
				));

		String actual = controller.getOrderDetails();

		assertEquals("[" +
						"{\"orderId\":1,\"productId\":2,\"quantity\":3}," +
						"{\"orderId\":4,\"productId\":5,\"quantity\":6}" +
						"]"
				, actual);
	}

	@Test
	public void getOrderDetailsList_none() {
		when(service.getAllOrderDetails())
				.thenReturn(List.of());

		String actual = controller.getOrderDetails();

		assertEquals("[]", actual);
	}

	@Test
	public void getOrder() {
		when(service.getOrder(anyLong()))
				.thenReturn(new Order(1, "id123", LocalDate.of(2021, 9, 1)));

		String actual = controller.getOrder(1L);

		assertEquals("{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}", actual);
	}

	@Test
	public void getOrder_notFound() {
		when(service.getOrder(anyLong()))
				.thenReturn(null);

		String actual = controller.getOrder(1L);

		assertEquals("null", actual);
	}

	@Test
	public void getOrderList() {
		when(service.getAllOrders())
				.thenReturn(Arrays.asList(
						new Order(1, "id123", LocalDate.of(2021, 9, 1)),
						new Order(2, "id124", LocalDate.of(2020, 10, 2))
				));

		String actual = controller.getOrder();

		assertEquals("[" +
						"{\"orderId\":1,\"customerId\":\"id123\",\"orderDate\":{\"year\":2021,\"month\":9,\"day\":1}}," +
						"{\"orderId\":2,\"customerId\":\"id124\",\"orderDate\":{\"year\":2020,\"month\":10,\"day\":2}}" +
						"]"
				, actual);
	}

	@Test
	public void getOrderList_none() {
		when(service.getAllOrders())
				.thenReturn(List.of());

		String actual = controller.getOrder();

		assertEquals("[]", actual);
	}

	@Test
	public void getProduct() {
		when(service.getProduct(anyLong()))
				.thenReturn(new Product(1, "productName", 2, 3, new BigDecimal("4")));

		String actual = controller.getProduct(1L);

		assertEquals("{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}", actual);
	}

	@Test
	public void getProduct_notFound() {
		when(service.getProduct(anyLong()))
				.thenReturn(null);

		String actual = controller.getProduct(1L);

		assertEquals("null", actual);
	}

	@Test
	public void getProductList() {
		when(service.getAllProducts())
				.thenReturn(Arrays.asList(
						new Product(1, "productName", 2, 3, new BigDecimal("4")),
						new Product(5, "productName2", 6, 7, new BigDecimal("8"))
				));

		String actual = controller.getProduct();

		assertEquals("[" +
						"{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}," +
						"{\"productId\":5,\"productName\":\"productName2\",\"supplierId\":6,\"categoryId\":7,\"unitPrice\":8}" +
						"]"
				, actual);
	}

	@Test
	public void getProductList_none() {
		when(service.getAllProducts())
				.thenReturn(List.of());

		String actual = controller.getProduct();

		assertEquals("[]", actual);
	}

	@Test
	public void getSupplier() {
		when(service.getSupplier(anyLong()))
				.thenReturn(new Supplier(1, "companyName", "contactName"));

		String actual = controller.getSupplier(1L);

		assertEquals("{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}", actual);
	}

	@Test
	public void getSupplier_notFound() {
		when(service.getSupplier(anyLong()))
				.thenReturn(null);

		String actual = controller.getSupplier(1L);

		assertEquals("null", actual);
	}

	@Test
	public void getSupplierList() {
		when(service.getAllSuppliers())
				.thenReturn(Arrays.asList(
						new Supplier(1, "companyName", "contactName"),
						new Supplier(2, "companyName2", "contactName2")
				));

		String actual = controller.getSupplier();

		assertEquals("[" +
						"{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}," +
						"{\"supplierId\":2,\"companyName\":\"companyName2\",\"contactName\":\"contactName2\"}" +
						"]"
				, actual);
	}

	@Test
	public void getSupplierList_none() {
		when(service.getAllSuppliers())
				.thenReturn(List.of());

		String actual = controller.getSupplier();

		assertEquals("[]", actual);
	}
}