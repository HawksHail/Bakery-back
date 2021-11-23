package com.cts.capstone.repository;

import com.cts.capstone.model.OrderDetails;
import com.cts.capstone.model.OrderDetailsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsKey> {
	List<OrderDetails> findAllByIdOrderId(Long id);
}
