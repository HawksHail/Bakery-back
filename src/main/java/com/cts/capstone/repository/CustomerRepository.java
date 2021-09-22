package com.cts.capstone.repository;

import com.cts.capstone.bean.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findByCompanyName(String name);

	Optional<Customer> findByContactName(String name);

	Optional<Customer> findByCustomerId(String customerId);
}
