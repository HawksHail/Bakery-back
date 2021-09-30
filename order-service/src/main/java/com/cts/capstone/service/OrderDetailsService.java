package com.cts.capstone.service;

import com.cts.capstone.exception.OrderDetailsNotFoundException;
import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailsService {

	private OrderDetailsRepository orderDetailsRepository;

	public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
		super();
		this.orderDetailsRepository = orderDetailsRepository;
	}

	public OrderDetailsRepository getOrderDetailsRepository() {
		return orderDetailsRepository;
	}

	public void setOrderDetailsService(OrderDetailsRepository categoryRepository) {
		this.orderDetailsRepository = categoryRepository;
	}

	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

	public OrderDetails findById(Long id) {
		return orderDetailsRepository.findById(id).orElseThrow(() -> new OrderDetailsNotFoundException(id));
	}

	public OrderDetails add(OrderDetails category) {
		return orderDetailsRepository.save(category);
	}

	public OrderDetails findByOrderIdAndProductId(Long orderId, Long productId) {
		return orderDetailsRepository.findByOrderIdAndProductId(orderId, productId).orElseThrow(() -> new OrderDetailsNotFoundException(orderId, productId));
	}

	public List<OrderDetails> findAllById(Long id) {
		return orderDetailsRepository.findAllByOrderId(id);
	}
}
