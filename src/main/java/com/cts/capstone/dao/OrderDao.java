package com.cts.capstone.dao;

import com.cts.capstone.bean.Order;

public interface OrderDao {
	Order getOrder(long orderId);
}
