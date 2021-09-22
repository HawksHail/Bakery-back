package com.cts.capstone.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "productid")
	private long productId;

	@Column(name = "productname", nullable = false, length = 40)
	private String productName;

	@Column(name = "supplierid")
	private long supplierId;

	@Column(name = "categoryid")
	private long categoryId;

	@Column(name = "unitprice", precision = 7, scale = 2)
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
	public int hashCode() {
		return Objects.hash(productId, productName, supplierId, categoryId, unitPrice);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return productId == product.productId && supplierId == product.supplierId && categoryId == product.categoryId && Objects.equals(productName, product.productName) && Objects.equals(unitPrice, product.unitPrice);
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", supplierId=" + supplierId +
				", categoryId=" + categoryId +
				", unitPrice=" + unitPrice +
				'}';
	}
}
