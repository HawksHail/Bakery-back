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

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderService(OrderRepository categoryRepository) {
		this.orderRepository = categoryRepository;
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
	}

	public Order add(Order category) {
		return orderRepository.save(category);
	}

	public List<Order> findByCustomerId(String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}
}
