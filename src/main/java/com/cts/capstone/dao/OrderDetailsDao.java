package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {

	boolean createOrderDetails(OrderDetails od);

	OrderDetails getOrderDetails(long orderId);

	List<OrderDetails> getAllOrderDetails();

	boolean updateOrderDetails(OrderDetails od);

	boolean deleteOrderDetails(long id);
}
