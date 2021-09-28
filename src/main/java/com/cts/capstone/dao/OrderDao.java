package com.cts.capstone.dao;

import com.cts.capstone.model.Order;

import java.util.List;

public interface OrderDao {
	boolean createOrder(Order o);

	Order getOrder(long orderId);

	List<Order> getAllOrders();

	boolean updateOrder(Order o);

	boolean deleteOrder(long id);

	List<Order> getOrdersForCustomer(String userId);
}
