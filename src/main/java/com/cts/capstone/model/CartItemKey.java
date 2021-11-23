package com.cts.capstone.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartItemKey implements Serializable {
	Long productId;
	Long customerId;

	public CartItemKey() {
	}

	public CartItemKey(Long productId, Long customerId) {
		this.productId = productId;
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, customerId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartItemKey that = (CartItemKey) o;
		return Objects.equals(productId, that.productId) && Objects.equals(customerId, that.customerId);
	}

	@Override
	public String toString() {
		return "CartItemKey{" +
				"productId=" + productId +
				", customerId=" + customerId +
				'}';
	}
}
