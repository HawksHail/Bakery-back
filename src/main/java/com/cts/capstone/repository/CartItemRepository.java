package com.cts.capstone.repository;

import com.cts.capstone.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findAllByCustomerCustomerId(Long CustomerId);

	Optional<CartItem> findByCustomerCustomerIdAndProductId(Long customerId, Long productId);
}
