package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.builder.*;
import com.cts.capstone.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DbServiceRepositoryTest {

	@Mock
	CategoryRepository categoryRepository;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	OrderRepository orderRepository;

	@Mock
	OrderDetailsRepository orderDetailsRepository;

	@Mock
	ProductRepository productRepository;

	@Mock
	SupplierRepository supplierRepository;

	DbServiceRepository serviceDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		serviceDao = new DbServiceRepository(categoryRepository, customerRepository, orderRepository, orderDetailsRepository, productRepository, supplierRepository);
	}

	@Test
	void getCategoryTest() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.of(CategoryBuilder.of(1, "name", "description")));

		Category category = serviceDao.getCategory(1);

		assertEquals(1, category.getCategoryId());
		verify(categoryRepository, times(1)).findById(anyLong());
	}

	@Test
	void getCategoryTest_notFound() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		Category category = serviceDao.getCategory(1);

		assertNull(category);
		verify(categoryRepository, times(1)).findById(anyLong());
	}

	@Test
	void getAllCategoriesTest() {
		List<Category> expectedList = new CategoryBuilder()
				.w(1, "name", "description")
				.w(2, "name2", "description2")
				.build();
		when(categoryRepository.findAll())
				.thenReturn(expectedList);

		List<Category> actual = new ArrayList<>();
		serviceDao.getAllCategories().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	void getAllCategoriesTest_empty() {
		List<Category> expectedList = new ArrayList<>(2);
		when(categoryRepository.findAll())
				.thenReturn(expectedList);

		List<Category> actual = new ArrayList<>();
		serviceDao.getAllCategories().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	void getCustomerTest() {
		when(customerRepository.findByCustomerId(anyString()))
				.thenReturn(Optional.of(CustomerBuilder.of("id1", "companyName", "contactName", "street", "city", "state")));

		Customer customer = serviceDao.getCustomer("id1");

		assertEquals("id1", customer.getCustomerId());
		verify(customerRepository, times(1)).findByCustomerId(anyString());
	}

	@Test
	void getCustomerTest_notFound() {
		when(customerRepository.findByCustomerId(anyString()))
				.thenReturn(Optional.empty());

		Customer customer = serviceDao.getCustomer("id1");

		assertNull(customer);
		verify(customerRepository, times(1)).findByCustomerId(anyString());
	}

	@Test
	void getAllCustomersTest() {
		List<Customer> expectedList = new CustomerBuilder()
				.w("id1", "companyName", "contactName", "street", "city", "state")
				.w("id2", "companyName2", "contactName2", "street2", "city2", "state2")
				.build();
		when(customerRepository.findAll())
				.thenReturn(expectedList);

		List<Customer> actual = new ArrayList<>();
		serviceDao.getAllCustomers().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	void getAllCustomersTest_empty() {
		List<Customer> expectedList = new ArrayList<>(2);
		when(customerRepository.findAll())
				.thenReturn(expectedList);

		List<Customer> actual = new ArrayList<>();
		serviceDao.getAllCustomers().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	void getOrderTest() {
		when(orderRepository.findById(anyLong()))
				.thenReturn(Optional.of(OrderBuilder.of(1L, "id1")));

		Order order = serviceDao.getOrder(1L);

		assertEquals(1, order.getOrderId());
		verify(orderRepository, times(1)).findById(anyLong());
	}

	@Test
	void getOrderTest_notFound() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		Order order = serviceDao.getOrder(1L);

		assertNull(order);
		verify(orderRepository, times(1)).findById(anyLong());
	}

	@Test
	void getAllOrdersTest() {
		List<Order> expectedList = new OrderBuilder()
				.w(1L, "id1")
				.w(2L, "id2")
				.build();
		when(orderRepository.findAll())
				.thenReturn(expectedList);

		List<Order> actual = new ArrayList<>();
		serviceDao.getAllOrders().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void getAllOrdersTest_empty() {
		List<Order> expectedList = new ArrayList<>(2);
		when(orderRepository.findAll())
				.thenReturn(expectedList);

		List<Order> actual = new ArrayList<>();
		serviceDao.getAllOrders().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void getOrderDetailsTest() {
		when(orderDetailsRepository.findById(anyLong()))
				.thenReturn(Optional.of(OrderDetailsBuilder.of(1L, 2L, 5)));

		OrderDetails orderDetails = serviceDao.getOrderDetails(1L);

		assertEquals(1L, orderDetails.getOrderId());
		verify(orderDetailsRepository, times(1)).findById(anyLong());
	}

	@Test
	void getOrderDetailsTest_notFound() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		OrderDetails orderDetails = serviceDao.getOrderDetails(1L);

		assertNull(orderDetails);
		verify(orderDetailsRepository, times(1)).findById(anyLong());
	}

	@Test
	void getAllOrderDetailsTest() {
		List<OrderDetails> expectedList = new OrderDetailsBuilder()
				.w(1L, 2L, 5)
				.w(3L, 4L, 7)
				.build();
		when(orderDetailsRepository.findAll())
				.thenReturn(expectedList);

		List<OrderDetails> actual = new ArrayList<>();
		serviceDao.getAllOrderDetails().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(orderDetailsRepository, times(1)).findAll();
	}

	@Test
	void getAllOrderDetailsTest_empty() {
		List<OrderDetails> expectedList = new ArrayList<>(2);
		when(orderDetailsRepository.findAll())
				.thenReturn(expectedList);

		List<OrderDetails> actual = new ArrayList<>();
		serviceDao.getAllOrderDetails().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(orderDetailsRepository, times(1)).findAll();
	}

	@Test
	void getProductTest() {
		when(productRepository.findById(anyLong()))
				.thenReturn(Optional.of(ProductBuilder.of(1L, "name", 2L, 3L, "4")));

		Product product = serviceDao.getProduct(1L);

		assertEquals(1L, product.getProductId());
		verify(productRepository, times(1)).findById(anyLong());
	}

	@Test
	void getProductTest_notFound() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		Product product = serviceDao.getProduct(1L);

		assertNull(product);
		verify(productRepository, times(1)).findById(anyLong());
	}

	@Test
	void getProductByCategory() {
		List<Product> expected = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(2L, "name2", 5L, 3L, "8")
				.build();
		when(productRepository.findByCategoryId(anyLong()))
				.thenReturn(expected);

		Iterable<Product> actual = serviceDao.getProductsByCategoryId(3L);

		assertEquals(expected, actual);
		verify(productRepository, times(1)).findByCategoryId(anyLong());
	}

	@Test
	void getAllProductsTest() {
		List<Product> expectedList = new ProductBuilder()
				.w(1L, "name", 2L, 3L, "4")
				.w(5L, "name2", 5L, 7L, "8")
				.build();
		when(productRepository.findAll())
				.thenReturn(expectedList);

		List<Product> actual = new ArrayList<>();
		serviceDao.getAllProducts().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	void getAllProductsTest_empty() {
		List<Product> expectedList = new ArrayList<>(2);
		when(productRepository.findAll())
				.thenReturn(expectedList);

		List<Product> actual = new ArrayList<>();
		serviceDao.getAllProducts().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	void getSupplierTest() {
		when(supplierRepository.findById(anyLong()))
				.thenReturn(Optional.of(SupplierBuilder.of(1L, "companyName", "contactName")));

		Supplier supplier = serviceDao.getSupplier(1L);

		assertEquals(1L, supplier.getSupplierId());
		verify(supplierRepository, times(1)).findById(anyLong());
	}

	@Test
	void getSupplierTest_notFound() {
		when(categoryRepository.findById(anyLong()))
				.thenReturn(Optional.empty());

		Supplier supplier = serviceDao.getSupplier(1L);

		assertNull(supplier);
		verify(supplierRepository, times(1)).findById(anyLong());
	}

	@Test
	void getAllSuppliersTest() {
		List<Supplier> expectedList = new SupplierBuilder()
				.w(1L, "companyName", "contactName")
				.w(2L, "companyName2", "contactName2")
				.build();
		when(supplierRepository.findAll())
				.thenReturn(expectedList);

		List<Supplier> actual = new ArrayList<>();
		serviceDao.getAllSuppliers().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(supplierRepository, times(1)).findAll();
	}

	@Test
	void getAllSuppliersTest_empty() {
		List<Supplier> expectedList = new ArrayList<>(2);
		when(supplierRepository.findAll())
				.thenReturn(expectedList);

		List<Supplier> actual = new ArrayList<>();
		serviceDao.getAllSuppliers().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(supplierRepository, times(1)).findAll();
	}
}