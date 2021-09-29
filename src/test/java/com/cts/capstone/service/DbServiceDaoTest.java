package com.cts.capstone.service;

import com.cts.capstone.builder.*;
import com.cts.capstone.dao.*;
import com.cts.capstone.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DbServiceDaoTest {

	@Mock
	CategoryDao categoryDao;

	@Mock
	CustomerDao customerDao;

	@Mock
	OrderDao orderDao;

	@Mock
	OrderDetailsDao orderDetailsDao;

	@Mock
	ProductDao productDao;

	@Mock
	SupplierDao supplierDao;

	DbServiceDao serviceDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		serviceDao = new DbServiceDao(categoryDao, customerDao, orderDao, orderDetailsDao, productDao, supplierDao);
	}

	@Test
	void getCategoryTest() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(CategoryBuilder.of(1, "name", "description"));

		Category category = serviceDao.getCategory(1);

		assertEquals(1, category.getCategoryId());
		verify(categoryDao, times(1)).getCategory(anyLong());
	}

	@Test
	void getCategoryTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		Category category = serviceDao.getCategory(100);

		assertNull(category);
		verify(categoryDao, times(1)).getCategory(anyLong());
	}

	@Test
	void getAllCategoriesTest() {
		List<Category> expectedList = new CategoryBuilder()
				.w(1, "name", "description")
				.w(2, "name2", "description2")
				.build();
		when(categoryDao.getAllCategories())
				.thenReturn(expectedList);

		List<Category> actual = serviceDao.getAllCategories();

		assertEquals(2, actual.size());
		verify(categoryDao, times(1)).getAllCategories();
	}

	@Test
	void getAllCategoriesTest_empty() {
		ArrayList<Category> expectedList = new ArrayList<>(2);
		when(categoryDao.getAllCategories())
				.thenReturn(expectedList);

		List<Category> actual = serviceDao.getAllCategories();

		assertEquals(0, actual.size());
		verify(categoryDao, times(1)).getAllCategories();
	}

	@Test
	void getCustomerTest() {
		when(customerDao.getCustomer(anyString()))
				.thenReturn(CustomerBuilder.of("id1", "companyName", "contactName", "street", "city", "state"));

		Customer customer = serviceDao.getCustomer("id1");

		assertEquals("id1", customer.getCustomerId());
		verify(customerDao, times(1)).getCustomer(anyString());
	}

	@Test
	void getCustomerTest_notFound() {
		when(customerDao.getCustomer(anyString()))
				.thenThrow(new NotFoundException());

		assertThrows(NotFoundException.class, () -> serviceDao.getCustomer("id1"));

		verify(customerDao, times(1)).getCustomer(anyString());
	}

	@Test
	void getAllCustomersTest() {
		List<Customer> expectedList = new CustomerBuilder()
				.w("id1", "companyName", "contactName", "street", "city", "state")
				.w("id2", "companyName2", "contactName2", "street2", "city2", "state2")
				.build();
		when(customerDao.getAllCustomers())
				.thenReturn(expectedList);

		List<Customer> actual = serviceDao.getAllCustomers();

		assertEquals(2, actual.size());
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	void getAllCustomersTest_empty() {
		List<Customer> expectedList = new ArrayList<>(2);
		when(customerDao.getAllCustomers())
				.thenReturn(expectedList);

		List<Customer> actual = serviceDao.getAllCustomers();

		assertEquals(0, actual.size());
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	void getOrderTest() {
		when(orderDao.getOrder(anyLong()))
				.thenReturn(OrderBuilder.of(1L, "id1"));

		Order order = serviceDao.getOrder(1L);

		assertEquals(1, order.getOrderId());
		verify(orderDao, times(1)).getOrder(anyLong());
	}

	@Test
	void getOrderTest_notFound() {
		when(orderDao.getOrder(anyLong()))
				.thenThrow(new NotFoundException());

		assertThrows(NotFoundException.class, () -> serviceDao.getOrder(1L));

		verify(orderDao, times(1)).getOrder(anyLong());
	}

	@Test
	void getAllOrdersTest() {
		List<Order> expectedList = new OrderBuilder()
				.w(1L, "id1")
				.w(2L, "id2")
				.build();
		when(orderDao.getAllOrders())
				.thenReturn(expectedList);

		List<Order> actual = serviceDao.getAllOrders();

		assertEquals(2, actual.size());
		verify(orderDao, times(1)).getAllOrders();
	}

	@Test
	void getAllOrdersTest_empty() {
		ArrayList<Order> expectedList = new ArrayList<>(2);
		when(orderDao.getAllOrders())
				.thenReturn(expectedList);

		List<Order> actual = serviceDao.getAllOrders();

		assertEquals(0, actual.size());
		verify(orderDao, times(1)).getAllOrders();
	}

	@Test
	void getOrderDetailsTest() {
		when(orderDetailsDao.getOrderDetails(anyLong()))
				.thenReturn(OrderDetailsBuilder.of(1L, 2L, 5));

		OrderDetails orderDetails = serviceDao.getOrderDetails(1L);

		assertEquals(1L, orderDetails.getOrderId());
		verify(orderDetailsDao, times(1)).getOrderDetails(anyLong());
	}

	@Test
	void getOrderDetailsTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		OrderDetails orderDetails = serviceDao.getOrderDetails(1L);

		assertNull(orderDetails);
		verify(orderDetailsDao, times(1)).getOrderDetails(anyLong());
	}

	@Test
	void getAllOrderDetailsTest() {
		List<OrderDetails> expectedList = new OrderDetailsBuilder()
				.w(1L, 2L, 5)
				.w(3L, 4L, 7)
				.build();
		when(orderDetailsDao.getAllOrderDetails())
				.thenReturn(expectedList);

		List<OrderDetails> actual = serviceDao.getAllOrderDetails();

		assertEquals(2, actual.size());
		verify(orderDetailsDao, times(1)).getAllOrderDetails();
	}

	@Test
	void getAllOrderDetailsTest_empty() {
		ArrayList<OrderDetails> expectedList = new ArrayList<>(2);
		when(orderDetailsDao.getAllOrderDetails())
				.thenReturn(expectedList);

		List<OrderDetails> actual = serviceDao.getAllOrderDetails();

		assertEquals(0, actual.size());
		verify(orderDetailsDao, times(1)).getAllOrderDetails();
	}

	@Test
	void getProductTest() {
		when(productDao.getProduct(anyLong()))
				.thenReturn(ProductBuilder.of(1L, "name", 2L, 3L, "4"));

		Product product = serviceDao.getProduct(1L);

		assertEquals(1L, product.getProductId());
		verify(productDao, times(1)).getProduct(anyLong());
	}

	@Test
	void getProductTest_notFound() {
		when(productDao.getProduct(anyLong()))
				.thenThrow(new NotFoundException());

		assertThrows(NotFoundException.class, () -> serviceDao.getProduct(1L));

		verify(productDao, times(1)).getProduct(anyLong());
	}

	@Test
	void getProductByCategory() {
		List<Product> expected = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(2L, "name2", 5L, 3L, "8")
				.build();
		when(productDao.getAllProductsByCategoryId(anyLong()))
				.thenReturn(expected);

		Iterable<Product> actual = serviceDao.getProductsByCategoryId(3L);

		assertEquals(expected, actual);
		verify(productDao, times(1)).getAllProductsByCategoryId(anyLong());
	}

	@Test
	void getAllProductsTest() {
		List<Product> expectedList = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(5L, "name2", 5L, 7L, "8")
				.build();
		when(productDao.getAllProducts())
				.thenReturn(expectedList);

		List<Product> actual = serviceDao.getAllProducts();

		assertEquals(2, actual.size());
		verify(productDao, times(1)).getAllProducts();
	}

	@Test
	void getAllProductsTest_empty() {
		ArrayList<Product> expectedList = new ArrayList<>(2);
		when(productDao.getAllProducts())
				.thenReturn(expectedList);

		List<Product> actual = serviceDao.getAllProducts();

		assertEquals(0, actual.size());
		verify(productDao, times(1)).getAllProducts();
	}

	@Test
	void getSupplierTest() {
		when(supplierDao.getSupplier(anyLong()))
				.thenReturn(SupplierBuilder.of(1L, "companyName", "contactName"));

		Supplier supplier = serviceDao.getSupplier(1L);

		assertEquals(1L, supplier.getSupplierId());
		verify(supplierDao, times(1)).getSupplier(anyLong());
	}

	@Test
	void getSupplierTest_notFound() {
		when(supplierDao.getSupplier(anyLong()))
				.thenThrow(new NotFoundException());

		assertThrows(NotFoundException.class, () -> serviceDao.getSupplier(1L));

		verify(supplierDao, times(1)).getSupplier(anyLong());
	}

	@Test
	void getAllSuppliersTest() {
		List<Supplier> expectedList = new SupplierBuilder()
				.w(1L, "companyName", "contactName")
				.w(2L, "companyName2", "contactName2")
				.build();
		when(supplierDao.getAllSuppliers())
				.thenReturn(expectedList);

		List<Supplier> actual = serviceDao.getAllSuppliers();

		assertEquals(2, actual.size());
		verify(supplierDao, times(1)).getAllSuppliers();
	}


	@Test
	void getAllSuppliersTest_empty() {
		ArrayList<Supplier> expectedList = new ArrayList<>(2);
		when(supplierDao.getAllSuppliers())
				.thenReturn(expectedList);

		List<Supplier> actual = serviceDao.getAllSuppliers();

		assertEquals(0, actual.size());
		verify(supplierDao, times(1)).getAllSuppliers();
	}

	@Test
	void getOrdersForCustomer() {
		List<Order> expectedList = new OrderBuilder()
				.w(1L, "id123")
				.w(2L, "id123")
				.build();
		when(orderDao.getAllOrders())
				.thenReturn(expectedList);

		List<Order> actual = serviceDao.getAllOrders();

		assertEquals(2, actual.size());
		verify(orderDao, times(1)).getAllOrders();
	}

	@Test
	void getDetailsForOrder() {
		List<OrderDetails> expectedList = new OrderDetailsBuilder()
				.w(1L, 2L, 5)
				.w(1L, 4L, 7)
				.build();
		when(orderDetailsDao.getAllOrderDetails())
				.thenReturn(expectedList);

		List<OrderDetails> actual = serviceDao.getAllOrderDetails();

		assertEquals(2, actual.size());
		verify(orderDetailsDao, times(1)).getAllOrderDetails();
	}
}