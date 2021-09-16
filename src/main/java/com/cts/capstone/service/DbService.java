package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderDetailsDao orderDetailsDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	SupplierDao supplierDao;

	public Category getCategory(long categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	public Customer getCustomer(String customerId) {
		return customerDao.getCustomer(customerId);
	}

	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	public Order getOrder(long orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	public OrderDetails getOrderDetails(long orderDetailsId) {
		return orderDetailsDao.getOrderDetails(orderDetailsId);
	}

	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsDao.getAllOrderDetails();
	}

	public Product getProduct(long productId) {
		return productDao.getProduct(productId);
	}

	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public Supplier getSupplier(long supplierId) {
		return supplierDao.getSupplier(supplierId);
	}

	public List<Supplier> getAllSuppliers() {
		return supplierDao.getAllSuppliers();
	}
}
