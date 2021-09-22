package com.cts.capstone.service;

import com.cts.capstone.bean.*;

public interface DbService {

	Category getCategory(long categoryId);

	Iterable<Category> getAllCategories();

	Customer getCustomer(String customerId);

	Iterable<Customer> getAllCustomers();

	Order getOrder(long orderId);

	Iterable<Order> getAllOrders();

	OrderDetails getOrderDetails(long orderDetailsId);

	Iterable<OrderDetails> getAllOrderDetails();

	Product getProduct(long productId);

	Iterable<Product> getAllProducts();

	Supplier getSupplier(long supplierId);

	Iterable<Supplier> getAllSuppliers();
}
