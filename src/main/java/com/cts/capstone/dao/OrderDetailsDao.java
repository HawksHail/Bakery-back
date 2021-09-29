package com.cts.capstone.dao;

import com.cts.capstone.model.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {

	boolean createOrderDetails(OrderDetails od);

	boolean createOrderDetailsList(OrderDetails[] details);

	List<OrderDetails> getOrderDetails(long orderId);

	OrderDetails getOrderDetails(long orderId, long productId);

	List<OrderDetails> getAllOrderDetails();

	boolean updateOrderDetails(OrderDetails od);

	boolean deleteOrderDetails(long id);

	List<OrderDetails> getOrderDetailsForOrder(long orderId);
}
