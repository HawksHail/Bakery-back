package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("DbServiceRepository")
@Primary
public class DbServiceRepository implements DbService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	SupplierRepository supplierRepository;

	public DbServiceRepository() {
	}

	public DbServiceRepository(CategoryRepository categoryRepository, CustomerRepository customerRepository,
	                           OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
	                           ProductRepository productRepository, SupplierRepository supplierRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.orderDetailsRepository = orderDetailsRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Category getCategory(long categoryId) {
		return categoryRepository.findById(categoryId).orElse(null);
	}

	@Override
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Customer getCustomer(String customerId) {
		return customerRepository.findByCustomerId(customerId).orElse(null);
	}

	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Order getOrder(long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	@Override
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public OrderDetails getOrderDetails(long orderDetailsId) {
		return orderDetailsRepository.findById(orderDetailsId).orElse(null);
	}

	@Override
	public Iterable<OrderDetails> getAllOrderDetails() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public Product getProduct(long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Supplier getSupplier(long supplierId) {
		return supplierRepository.findById(supplierId).orElse(null);
	}

	@Override
	public Iterable<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}
}
