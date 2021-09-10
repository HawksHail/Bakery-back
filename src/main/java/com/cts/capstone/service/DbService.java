package com.cts.capstone.service;

import com.cts.capstone.bean.*;
import com.cts.capstone.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Customer getCustomer(String customerId) {
		return customerDao.getCustomer(customerId);
	}

	public Order getOrder(long orderId) {
		return orderDao.getOrder(orderId);
	}

	public OrderDetails getOrderDetails(long orderDetailsId) {
		return orderDetailsDao.getOrderDetails(orderDetailsId);
	}

	public Product getProduct(long productId) {
		return productDao.getProduct(productId);
	}

	public Supplier getSupplier(long supplierId) {
		return supplierDao.getSupplier(supplierId);
	}
}
