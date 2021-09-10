package com.cts.capstone.bean;

import java.math.BigDecimal;

public class Product {
	private long productId;
	private String productName;
	private long supplierId;
	private long categoryId;
	private BigDecimal unitPrice;

	public Product() {
	}

	public Product(long productId, String productName, long supplierId, long categoryId, BigDecimal unitPrice) {
		this.productId = productId;
		this.productName = productName;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.unitPrice = unitPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = new BigDecimal(unitPrice);
	}

	@Override
	public String toString() {
		return "Products{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", supplierId=" + supplierId +
				", categoryId=" + categoryId +
				", unitPrice=" + unitPrice +
				'}';
	}
}
