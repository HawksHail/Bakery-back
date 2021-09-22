package com.cts.capstone.repository;

import com.cts.capstone.bean.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
	Optional<Order> findByCustomerId(Long customerId);
}
