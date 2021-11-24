package com.cts.capstone.repository;

import com.cts.capstone.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByCustomerCustomerId(Long customerId);
}
