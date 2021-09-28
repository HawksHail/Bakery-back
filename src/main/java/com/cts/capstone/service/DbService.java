package com.cts.capstone.service;

import com.cts.capstone.model.*;

import java.util.List;

public interface DbService {

	boolean createCategory(Category c);

	Category getCategory(long categoryId);

	List<Category> getAllCategories();

	boolean updateCategory(Category c);

	boolean deleteCategory(long categoryId);

	boolean createCustomer(Customer c);

	Customer getCustomer(String customerId);

	List<Customer> getAllCustomers();

	boolean updateCustomer(Customer c);

	boolean deleteCustomer(String id);

	boolean createOrder(Order o);

	Order getOrder(long orderId);

	List<Order> getAllOrders();

	boolean updateOrder(Order o);

	boolean deleteOrder(long id);

	boolean createOrderDetails(OrderDetails od);

	OrderDetails getOrderDetails(long orderDetailsId);

	List<OrderDetails> getAllOrderDetails();

	boolean updateOrderDetails(OrderDetails od);

	boolean deleteOrderDetails(long id);

	boolean createProduct(Product p);

	Product getProduct(long productId);

	List<Product> getAllProducts();

	boolean updateProduct(Product p);

	boolean deleteProduct(long id);

	boolean createSupplier(Supplier s);

	Supplier getSupplier(long supplierId);

	List<Supplier> getAllSuppliers();

	boolean updateSupplier(Supplier s);

	boolean deleteSupplier(long id);

	List<Product> getProductsByCategoryId(long categoryId);

	List<Order> getOrdersForCustomer(String userId);

	List<OrderDetails> getDetailsForOrder(long orderId);
}
