package com.cts.capstone.repository;

import com.cts.capstone.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	Optional<OrderDetails> findByIdOrderIdAndIdProductId(Long orderId, Long productId);

	List<OrderDetails> findAllByIdOrderId(Long id);
}
