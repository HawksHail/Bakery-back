package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;

import java.util.List;

public interface OrderDetailsDao {

	OrderDetails getOrderDetails(long orderId);

	List<OrderDetails> getAllOrderDetails();
}
