package com.cts.capstone.service;

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

	public void setOrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
		this.orderDetailsRepository = orderDetailsRepository;
	}

	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

	public OrderDetails findById(Long id) {
		return orderDetailsRepository.findById(id).orElse(null);
	}

	public OrderDetails add(OrderDetails orderDetails) {
		return orderDetailsRepository.save(orderDetails);
	}

	public List<OrderDetails> addList(List<OrderDetails> list) {
		return orderDetailsRepository.saveAll(list);
	}

	public OrderDetails findByOrderIdAndProductId(Long orderId, Long productId) {
		return orderDetailsRepository.findByOrderIdAndProductId(orderId, productId).orElse(null);
	}

	public List<OrderDetails> findAllById(Long id) {
		return orderDetailsRepository.findAllByOrderId(id);
	}
}
