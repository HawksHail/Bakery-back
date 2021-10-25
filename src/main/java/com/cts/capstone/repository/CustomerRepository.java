package com.cts.capstone.repository;

import com.cts.capstone.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByCustomerId(String id);

	void deleteByCustomerId(String id);
}
