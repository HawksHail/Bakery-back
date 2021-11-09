package com.cts.capstone.service;

import com.cts.capstone.model.CartItem;
import com.cts.capstone.model.Customer;
import com.cts.capstone.model.Product;
import com.cts.capstone.repository.CartItemRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

	private CartItemRepository cartItemRepository;

	public CartItemService(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}

	public CartItemRepository getCartItemRepository() {
		return cartItemRepository;
	}

	public void setCartItemRepository(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}

	public CartItem findById(Long customerId, Long productId) {
		return cartItemRepository.findByCustomerCustomerIdAndProductId(customerId, productId).orElse(null);
	}

	@PreAuthorize("#customer.sub == authentication.name or hasAuthority('update:customer')")
	public void add(Customer customer, Product product) {
		Optional<CartItem> item = cartItemRepository.findByCustomerCustomerIdAndProductId(customer.getCustomerId(), product.getId());
		if (item.isPresent()) {
			item.get().add();
			cartItemRepository.save(item.get());
		} else {
			cartItemRepository.save(new CartItem(customer, product, 1));
		}
	}

	@PreAuthorize("#customer.sub == authentication.name or hasAuthority('update:customer')")
	public void remove(Customer customer, Product product) {
		Optional<CartItem> item = cartItemRepository.findByCustomerCustomerIdAndProductId(customer.getCustomerId(), product.getId());
		if (item.isPresent()) {
			int quantity = item.get().remove();
			if (quantity < 1) {
				cartItemRepository.delete(item.get());
			} else {
				cartItemRepository.save(item.get());
			}
		}
	}

	public void clear(Long customerId) {
		cartItemRepository.deleteAllInBatch(findAllByCustomerId(customerId));
	}

	public List<CartItem> findAllByCustomerId(Long customerId) {
		return cartItemRepository.findAllByCustomerCustomerId(customerId);
	}
}
