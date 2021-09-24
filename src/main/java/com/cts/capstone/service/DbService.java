package com.cts.capstone.service;

import com.cts.capstone.bean.*;

public interface DbService {

	boolean createCategory(Category c);

	Category getCategory(long categoryId);

	Iterable<Category> getAllCategories();

	boolean updateCategory(Category c);

	boolean deleteCategory(long categoryId);

	boolean createCustomer(Customer c);

	Customer getCustomer(String customerId);

	Iterable<Customer> getAllCustomers();

	boolean updateCustomer(Customer c);

	boolean deleteCustomer(String id);

	boolean createOrder(Order o);

	Order getOrder(long orderId);

	Iterable<Order> getAllOrders();

	boolean updateOrder(Order o);

	boolean deleteOrder(long id);

	boolean createOrderDetails(OrderDetails od);

	OrderDetails getOrderDetails(long orderDetailsId);

	Iterable<OrderDetails> getAllOrderDetails();

	boolean updateOrderDetails(OrderDetails od);

	boolean deleteOrderDetails(long id);

	boolean createProduct(Product p);

	Product getProduct(long productId);

	Iterable<Product> getAllProducts();

	boolean updateProduct(Product p);

	boolean deleteProduct(long id);


	Iterable<Product> getProductsByCategoryId(long categoryId);

	boolean createSupplier(Supplier s);

	Supplier getSupplier(long supplierId);

	Iterable<Supplier> getAllSuppliers();

	boolean updateSupplier(Supplier s);

	boolean deleteSupplier(long id);
}
