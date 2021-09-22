package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DbServiceDao")
public class DbServiceDao implements DbService {

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

	public DbServiceDao() {
	}

	public DbServiceDao(CategoryDao categoryDao, CustomerDao customerDao, OrderDao orderDao, OrderDetailsDao orderDetailsDao, ProductDao productDao, SupplierDao supplierDao) {
		this.categoryDao = categoryDao;
		this.customerDao = customerDao;
		this.orderDao = orderDao;
		this.orderDetailsDao = orderDetailsDao;
		this.productDao = productDao;
		this.supplierDao = supplierDao;
	}

	public DbServiceDao(JdbcTemplate jdbcTemplate) {
		this.categoryDao = new CategoryDaoImpl(jdbcTemplate);
		this.customerDao = new CustomerDaoImpl(jdbcTemplate);
		this.orderDao = new OrderDaoImpl(jdbcTemplate);
		this.orderDetailsDao = new OrderDetailsDaoImpl(jdbcTemplate);
		this.productDao = new ProductDaoImpl(jdbcTemplate);
		this.supplierDao = new SupplierDaoImpl(jdbcTemplate);
	}

	@Override
	public Category getCategory(long categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAllCategories();
	}

	@Override
	public Customer getCustomer(String customerId) {
		return customerDao.getCustomer(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@Override
	public Order getOrder(long orderId) {
		return orderDao.getOrder(orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public OrderDetails getOrderDetails(long orderDetailsId) {
		return orderDetailsDao.getOrderDetails(orderDetailsId);
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsDao.getAllOrderDetails();
	}

	@Override
	public Product getProduct(long productId) {
		return productDao.getProduct(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Supplier getSupplier(long supplierId) {
		return supplierDao.getSupplier(supplierId);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierDao.getAllSuppliers();
	}
}
