package com.cts.capstone.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsKey implements Serializable {

	long orderId;
	long productId;

	public OrderDetailsKey() {
		//Empty
	}

	public OrderDetailsKey(long orderId, long productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
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
		return orderId == that.orderId && productId == that.productId;
	}

	@Override
	public String toString() {
		return "OrderDetailsKey{" +
				"orderId=" + orderId +
				", productId=" + productId +
				'}';
	}
}
