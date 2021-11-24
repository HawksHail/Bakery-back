package com.cts.capstone.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsKey implements Serializable {

	Long orderId;
	Long productId;

	public OrderDetailsKey() {
		//Empty
	}

	public OrderDetailsKey(Long orderId, Long productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDetailsKey that = (OrderDetailsKey) o;
		return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId);
	}

	@Override
	public String toString() {
		return "OrderDetailsKey{" +
				"orderId=" + orderId +
				", productId=" + productId +
				'}';
	}
}
