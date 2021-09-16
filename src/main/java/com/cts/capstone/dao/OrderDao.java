package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;

import java.util.List;

public interface OrderDao {

	Order getOrder(long orderId);

	List<Order> getAllOrders();
}
