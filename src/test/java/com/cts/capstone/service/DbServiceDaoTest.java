package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
				.thenReturn(new Category(1, "name", "description"));

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
		ArrayList<Category> expectedList = new ArrayList<>(2);
		expectedList.add(new Category(1, "name", "description"));
		expectedList.add(new Category(2, "name2", "description2"));
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
				.thenReturn(new Customer("id1", "companyName", "contactName", "street", "city", "state"));

		Customer customer = serviceDao.getCustomer("id1");

		assertEquals("id1", customer.getCustomerId());
		verify(customerDao, times(1)).getCustomer(anyString());
	}

	@Test
	void getCustomerTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		Customer customer = serviceDao.getCustomer("id1");

		assertNull(customer);
		verify(customerDao, times(1)).getCustomer(anyString());
	}

	@Test
	void getAllCustomersTest() {
		ArrayList<Customer> expectedList = new ArrayList<>(2);
		expectedList.add(new Customer("id1", "companyName", "contactName", "street", "city", "state"));
		expectedList.add(new Customer("id2", "companyName2", "contactName2", "street2", "city2", "state2"));
		when(customerDao.getAllCustomers())
				.thenReturn(expectedList);

		List<Customer> actual = serviceDao.getAllCustomers();

		assertEquals(2, actual.size());
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	void getAllCustomersTest_empty() {
		ArrayList<Customer> expectedList = new ArrayList<>(2);
		when(customerDao.getAllCustomers())
				.thenReturn(expectedList);

		List<Customer> actual = serviceDao.getAllCustomers();

		assertEquals(0, actual.size());
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	void getOrderTest() {
		when(orderDao.getOrder(anyLong()))
				.thenReturn(new Order(1L, "id1", LocalDate.of(2020, 9, 17)));

		Order order = serviceDao.getOrder(1L);

		assertEquals(1, order.getOrderId());
		verify(orderDao, times(1)).getOrder(anyLong());
	}

	@Test
	void getOrderTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		Order order = serviceDao.getOrder(1L);

		assertNull(order);
		verify(orderDao, times(1)).getOrder(anyLong());
	}

	@Test
	void getAllOrdersTest() {
		ArrayList<Order> expectedList = new ArrayList<>(2);
		expectedList.add(new Order(1L, "id1", LocalDate.of(2020, 9, 17)));
		expectedList.add(new Order(2L, "id2", LocalDate.of(2020, 9, 16)));
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
				.thenReturn(new OrderDetails(1L, 2L, 5));

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
		ArrayList<OrderDetails> expectedList = new ArrayList<>(2);
		expectedList.add(new OrderDetails(1L, 2L, 5));
		expectedList.add(new OrderDetails(3L, 4L, 7));
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
				.thenReturn(new Product(1L, "name", 2L, 3L, new BigDecimal("4")));

		Product product = serviceDao.getProduct(1L);

		assertEquals(1L, product.getProductId());
		verify(productDao, times(1)).getProduct(anyLong());
	}

	@Test
	void getProductTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		Product product = serviceDao.getProduct(1L);

		assertNull(product);
		verify(productDao, times(1)).getProduct(anyLong());
	}

	@Test
	void getAllProductsTest() {
		ArrayList<Product> expectedList = new ArrayList<>(2);
		expectedList.add(new Product(1L, "name", 2L, 3L, new BigDecimal("4")));
		expectedList.add(new Product(5L, "name2", 5L, 7L, new BigDecimal("8")));
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
				.thenReturn(new Supplier(1L, "companyName", "contactName"));

		Supplier supplier = serviceDao.getSupplier(1L);

		assertEquals(1L, supplier.getSupplierId());
		verify(supplierDao, times(1)).getSupplier(anyLong());
	}

	@Test
	void getSupplierTest_notFound() {
		when(categoryDao.getCategory(anyLong()))
				.thenReturn(null);

		Supplier supplier = serviceDao.getSupplier(1L);

		assertNull(supplier);
		verify(supplierDao, times(1)).getSupplier(anyLong());
	}

	@Test
	void getAllSuppliersTest() {
		ArrayList<Supplier> expectedList = new ArrayList<>(2);
		expectedList.add(new Supplier(1L, "companyName", "contactName"));
		expectedList.add(new Supplier(2L, "companyName2", "contactName2"));
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
}