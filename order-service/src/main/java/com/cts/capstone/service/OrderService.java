package com.cts.capstone.service;

import com.cts.capstone.exception.OrderNotFoundException;
import com.cts.capstone.model.Order;
import com.cts.capstone.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public void setOrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
	}

	public Order add(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findByCustomerId(String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}
}
