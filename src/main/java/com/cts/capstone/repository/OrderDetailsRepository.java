package com.cts.capstone.repository;

import com.cts.capstone.bean.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
//	public Optional<OrderDetails> findByCustomerId(Long customerId);
}
