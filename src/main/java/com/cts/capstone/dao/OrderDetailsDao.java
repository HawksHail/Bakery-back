package com.cts.capstone.dao;

import com.cts.capstone.bean.OrderDetails;

public interface OrderDetailsDao {
	OrderDetails getOrderDetails(long orderId);
}
