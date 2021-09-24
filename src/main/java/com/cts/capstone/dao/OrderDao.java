package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;

import java.util.List;

public interface OrderDao {
	boolean createOrder(Order o);

	Order getOrder(long orderId);

	List<Order> getAllOrders();

	boolean updateOrder(Order o);

	boolean deleteOrder(long id);
}
