package com.cts.capstone.controller;

import com.cts.capstone.builder.CategoryBuilder;
import com.cts.capstone.builder.CustomerBuilder;
import com.cts.capstone.builder.ProductBuilder;
import com.cts.capstone.builder.SupplierBuilder;
import com.cts.capstone.model.Category;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.model.Supplier;
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

class ProductControllerTest {

	private final static Gson gson = new Gson();

	@Mock
	DbService service;

	ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productController = new ProductController(service);
	}

	@Test
	void getCategory() {
		Category expected = CategoryBuilder.of(1, "name", "description");
		when(service.getCategory(anyLong()))
				.thenReturn(expected);

		String json = productController.getCategory(1L);
		Category actual = gson.fromJson(json, Category.class);

		assertEquals("{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getCategory_notFound() {
		when(service.getCategory(anyLong()))
				.thenReturn(null);

		String json = productController.getCategory(1L);
		Category actual = gson.fromJson(json, Category.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getCategoryList() {
		List<Category> list = new CategoryBuilder()
				.w(1, "name", "description")
				.w(2, "name2", "description2")
				.build();
		when(service.getAllCategories())
				.thenReturn(list);

		String json = productController.getCategory();
		Category[] actual = gson.fromJson(json, Category[].class);

		assertEquals("[" +
						"{\"categoryId\":1,\"categoryName\":\"name\",\"description\":\"description\"}," +
						"{\"categoryId\":2,\"categoryName\":\"name2\",\"description\":\"description2\"}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	void getCategoryList_none() {
		when(service.getAllCategories())
				.thenReturn(List.of());

		String json = productController.getCategory();
		Category[] actual = gson.fromJson(json, Category[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void getCustomer() {
		Customer expected = CustomerBuilder.of("id123", "companyName", "contactName", "street", "city", "state");
		when(service.getCustomer(anyString()))
				.thenReturn(expected);

		String json = productController.getCustomer("id123");
		Customer actual = gson.fromJson(json, Customer.class);

		assertEquals("{\"customerId\":\"id123\",\"companyName\":\"companyName\",\"contactName\":\"contactName\",\"street\":\"street\",\"city\":\"city\",\"state\":\"state\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getCustomer_notFound() {
		when(service.getCustomer(anyString()))
				.thenReturn(null);

		String json = productController.getCustomer("id123");
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

		String json = productController.getCustomer();
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

		String json = productController.getCustomer();
		Customer[] actual = gson.fromJson(json, Customer[].class);


		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void getProduct() {
		Product expected = ProductBuilder.of(1, "productName", 2, 3, "4");
		when(service.getProduct(anyLong()))
				.thenReturn(expected);

		String json = productController.getProduct(1L);
		Product actual = gson.fromJson(json, Product.class);

		assertEquals("{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getProduct_notFound() {
		when(service.getProduct(anyLong()))
				.thenReturn(null);

		String json = productController.getProduct(1L);
		Product actual = gson.fromJson(json, Product.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getProductByCategory() {
		List<Product> expected = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(2L, "name2", 5L, 3L, "8")
				.build();
		when(service.getProductsByCategoryId(anyLong()))
				.thenReturn(expected);

		String json = productController.getProductByCategory(3L);
		Product[] actual = gson.fromJson(json, Product[].class);

		assertEquals("[" +
						"{\"productId\":1,\"productName\":\"name\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}," +
						"{\"productId\":2,\"productName\":\"name2\",\"supplierId\":5,\"categoryId\":3,\"unitPrice\":8}" +
						"]"
				, json);
		assertEquals(expected, List.of(actual));
	}

	@Test
	void getProductList() {
		List<Product> list = new ProductBuilder()
				.w(1, "productName", 2, 3, "4")
				.w(5, "productName2", 6, 7, "8")
				.build();
		when(service.getAllProducts())
				.thenReturn(list);

		String json = productController.getProduct();
		Product[] actual = gson.fromJson(json, Product[].class);

		assertEquals("[" +
						"{\"productId\":1,\"productName\":\"productName\",\"supplierId\":2,\"categoryId\":3,\"unitPrice\":4}," +
						"{\"productId\":5,\"productName\":\"productName2\",\"supplierId\":6,\"categoryId\":7,\"unitPrice\":8}" +
						"]"
				, json);
		assertEquals(list, List.of(actual));
	}

	@Test
	void getProductList_none() {
		when(service.getAllProducts())
				.thenReturn(List.of());

		String json = productController.getProduct();
		Product[] actual = gson.fromJson(json, Product[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}

	@Test
	void getSupplier() {
		Supplier expected = SupplierBuilder.of(1, "companyName", "contactName");
		when(service.getSupplier(anyLong()))
				.thenReturn(expected);

		String json = productController.getSupplier(1L);
		Supplier actual = gson.fromJson(json, Supplier.class);

		assertEquals("{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}", json);
		assertEquals(expected, actual);
	}

	@Test
	void getSupplier_notFound() {
		when(service.getSupplier(anyLong()))
				.thenReturn(null);

		String json = productController.getSupplier(1L);
		Supplier actual = gson.fromJson(json, Supplier.class);

		assertEquals("null", json);
		assertNull(actual);
	}

	@Test
	void getSupplierList() {
		List<Supplier> expected = new SupplierBuilder()
				.w(1, "companyName", "contactName")
				.w(2, "companyName2", "contactName2")
				.build();
		when(service.getAllSuppliers())
				.thenReturn(expected);

		String json = productController.getSupplier();
		Supplier[] actual = gson.fromJson(json, Supplier[].class);

		assertEquals("[" +
						"{\"supplierId\":1,\"companyName\":\"companyName\",\"contactName\":\"contactName\"}," +
						"{\"supplierId\":2,\"companyName\":\"companyName2\",\"contactName\":\"contactName2\"}" +
						"]"
				, json);
		assertEquals(expected, List.of(actual));
	}

	@Test
	void getSupplierList_none() {
		when(service.getAllSuppliers())
				.thenReturn(List.of());

		String json = productController.getSupplier();
		Supplier[] actual = gson.fromJson(json, Supplier[].class);

		assertEquals("[]", json);
		assertEquals(List.of(), List.of(actual));
	}
}