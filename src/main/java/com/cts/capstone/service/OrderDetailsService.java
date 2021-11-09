package com.cts.capstone.service;

import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.model.OrderDetailsKey;
import com.cts.capstone.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

	public void setOrderDetailsRepository(OrderDetailsRepository orderDetailsRepository) {
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
		return orderDetailsRepository.findByIdOrderIdAndIdProductId(orderId, productId).orElse(null);
	}

	public List<OrderDetails> findAllById(Long id) {
		return orderDetailsRepository.findAllByIdOrderId(id);
	}

	public boolean delete(OrderDetailsKey key) {
		Optional<OrderDetails> details = orderDetailsRepository.findById(key);
		if (details.isPresent()) {
			orderDetailsRepository.deleteById(key);
			return true;
		} else {
			return false;
		}
	}
}
