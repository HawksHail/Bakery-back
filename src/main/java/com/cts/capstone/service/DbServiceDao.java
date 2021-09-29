package com.cts.capstone.service;

import com.cts.capstone.dao.*;
import com.cts.capstone.model.*;
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
		//Empty
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
	public boolean createCategory(Category c) {
		return categoryDao.createCategory(c);
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
	public boolean updateCategory(Category c) {
		return categoryDao.updateCategory(c);
	}

	@Override
	public boolean deleteCategory(long categoryId) {
		return categoryDao.deleteCategory(categoryId);
	}

	@Override
	public boolean createCustomer(Customer c) {
		return customerDao.createCustomer(c);
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
	public boolean updateCustomer(Customer c) {
		return customerDao.updateCustomer(c);
	}

	@Override
	public boolean deleteCustomer(String id) {
		return customerDao.deleteCustomer(id);
	}

	@Override
	public boolean createOrder(Order o) {
		return orderDao.createOrder(o);
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
	public boolean updateOrder(Order o) {
		return orderDao.updateOrder(o);
	}

	@Override
	public boolean deleteOrder(long id) {
		return orderDao.deleteOrder(id);
	}

	@Override
	public boolean createOrderDetails(OrderDetails od) {
		return orderDetailsDao.createOrderDetails(od);
	}

	@Override
	public boolean createOrderDetailsList(OrderDetails[] details) {
		return orderDetailsDao.createOrderDetailsList(details);
	}

	@Override
	public List<OrderDetails> getOrderDetails(long orderDetailsId) {
		return orderDetailsDao.getOrderDetails(orderDetailsId);
	}

	@Override
	public OrderDetails getOrderDetails(long orderDetailsId, long productId) {
		return orderDetailsDao.getOrderDetails(orderDetailsId, productId);
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsDao.getAllOrderDetails();
	}

	@Override
	public boolean updateOrderDetails(OrderDetails od) {
		return orderDetailsDao.updateOrderDetails(od);
	}

	@Override
	public boolean deleteOrderDetails(long id) {
		return orderDetailsDao.deleteOrderDetails(id);
	}

	@Override
	public boolean createProduct(Product p) {
		return productDao.createProduct(p);
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
	public boolean updateProduct(Product p) {
		return productDao.updateProduct(p);
	}

	@Override
	public boolean deleteProduct(long id) {
		return productDao.deleteProduct(id);
	}

	@Override
	public boolean createSupplier(Supplier s) {
		return supplierDao.createSupplier(s);
	}

	@Override
	public Supplier getSupplier(long supplierId) {
		return supplierDao.getSupplier(supplierId);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierDao.getAllSuppliers();
	}

	@Override
	public boolean updateSupplier(Supplier s) {
		return supplierDao.updateSupplier(s);
	}

	@Override
	public boolean deleteSupplier(long id) {
		return supplierDao.deleteSupplier(id);
	}

	@Override
	public List<Product> getProductsByCategoryId(long categoryId) {
		return productDao.getAllProductsByCategoryId(categoryId);
	}

	@Override
	public List<Order> getOrdersForCustomer(String userId) {
		return orderDao.getOrdersForCustomer(userId);
	}

	@Override
	public List<OrderDetails> getDetailsForOrder(long orderId) {
		return orderDetailsDao.getOrderDetailsForOrder(orderId);
	}

}
