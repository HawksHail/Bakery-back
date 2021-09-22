package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
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
				.thenReturn(java.util.Optional.of(new Category(1, "name", "description")));

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
		ArrayList<Category> expectedList = new ArrayList<>(2);
		expectedList.add(new Category(1, "name", "description"));
		expectedList.add(new Category(2, "name2", "description2"));
		when(categoryRepository.findAll())
				.thenReturn(expectedList);

		List<Category> actual = new ArrayList<>();
		serviceDao.getAllCategories().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(categoryRepository, times(1)).findAll();
	}

	@Test
	void getAllCategoriesTest_empty() {
		ArrayList<Category> expectedList = new ArrayList<>(2);
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
				.thenReturn(java.util.Optional.of(new Customer("id1", "companyName", "contactName", "street", "city", "state")));

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
		ArrayList<Customer> expectedList = new ArrayList<>(2);
		expectedList.add(new Customer("id1", "companyName", "contactName", "street", "city", "state"));
		expectedList.add(new Customer("id2", "companyName2", "contactName2", "street2", "city2", "state2"));
		when(customerRepository.findAll())
				.thenReturn(expectedList);

		List<Customer> actual = new ArrayList<>();
		serviceDao.getAllCustomers().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	void getAllCustomersTest_empty() {
		ArrayList<Customer> expectedList = new ArrayList<>(2);
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
				.thenReturn(java.util.Optional.of(new Order(1L, "id1", LocalDate.of(2020, 9, 17))));

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
		ArrayList<Order> expectedList = new ArrayList<>(2);
		expectedList.add(new Order(1L, "id1", LocalDate.of(2020, 9, 17)));
		expectedList.add(new Order(2L, "id2", LocalDate.of(2020, 9, 16)));
		when(orderRepository.findAll())
				.thenReturn(expectedList);

		List<Order> actual = new ArrayList<>();
		serviceDao.getAllOrders().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void getAllOrdersTest_empty() {
		ArrayList<Order> expectedList = new ArrayList<>(2);
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
				.thenReturn(java.util.Optional.of(new OrderDetails(1L, 2L, 5)));

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
		ArrayList<OrderDetails> expectedList = new ArrayList<>(2);
		expectedList.add(new OrderDetails(1L, 2L, 5));
		expectedList.add(new OrderDetails(3L, 4L, 7));
		when(orderDetailsRepository.findAll())
				.thenReturn(expectedList);

		List<OrderDetails> actual = new ArrayList<>();
		serviceDao.getAllOrderDetails().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(orderDetailsRepository, times(1)).findAll();
	}

	@Test
	void getAllOrderDetailsTest_empty() {
		ArrayList<OrderDetails> expectedList = new ArrayList<>(2);
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
				.thenReturn(java.util.Optional.of(new Product(1L, "name", 2L, 3L, new BigDecimal("4"))));

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
	void getAllProductsTest() {
		ArrayList<Product> expectedList = new ArrayList<>(2);
		expectedList.add(new Product(1L, "name", 2L, 3L, new BigDecimal("4")));
		expectedList.add(new Product(5L, "name2", 5L, 7L, new BigDecimal("8")));
		when(productRepository.findAll())
				.thenReturn(expectedList);

		List<Product> actual = new ArrayList<>();
		serviceDao.getAllProducts().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	void getAllProductsTest_empty() {
		ArrayList<Product> expectedList = new ArrayList<>(2);
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
				.thenReturn(java.util.Optional.of(new Supplier(1L, "companyName", "contactName")));

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
		ArrayList<Supplier> expectedList = new ArrayList<>(2);
		expectedList.add(new Supplier(1L, "companyName", "contactName"));
		expectedList.add(new Supplier(2L, "companyName2", "contactName2"));
		when(supplierRepository.findAll())
				.thenReturn(expectedList);

		List<Supplier> actual = new ArrayList<>();
		serviceDao.getAllSuppliers().forEach(actual::add);

		assertEquals(2, actual.size());
		verify(supplierRepository, times(1)).findAll();
	}

	@Test
	void getAllSuppliersTest_empty() {
		ArrayList<Supplier> expectedList = new ArrayList<>(2);
		when(supplierRepository.findAll())
				.thenReturn(expectedList);

		List<Supplier> actual = new ArrayList<>();
		serviceDao.getAllSuppliers().forEach(actual::add);

		assertEquals(0, actual.size());
		verify(supplierRepository, times(1)).findAll();
	}
}