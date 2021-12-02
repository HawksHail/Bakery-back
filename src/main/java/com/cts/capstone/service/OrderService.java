package com.cts.capstone.service;

import com.cts.capstone.model.Order;
import com.cts.capstone.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public Order add(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findByCustomerId(Long customerId) {
		return orderRepository.findByCustomerCustomerIdOrderByOrderDateDescIdDesc(customerId);
	}

	public boolean delete(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			orderRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
