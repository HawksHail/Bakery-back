package com.cts.capstone.service;

import com.cts.capstone.model.Order;
import com.cts.capstone.repository.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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
		return orderRepository.findById(id).orElse(null);
	}

	public Order add(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findByCustomerId(String customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	public void delete(Order order) {
		try {
			orderRepository.delete(order);
		} catch (IllegalArgumentException ignored) {
		}
	}

	public void delete(Long id) {
		try {
			orderRepository.deleteById(id);
		} catch (IllegalArgumentException | EmptyResultDataAccessException ignored) {
		}
	}
}
