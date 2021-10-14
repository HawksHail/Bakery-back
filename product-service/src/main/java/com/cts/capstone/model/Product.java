package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "productid")
	private long id;

	@Column(name = "productname", nullable = false, length = 40)
	@Length(max = 40, message = "Product name max length 40")
	private String productName;

	@Column(name = "supplierid")
	private long supplierId;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	@JsonIgnoreProperties("productList")
	private Category category;

	@Column(name = "unitprice", precision = 7, scale = 2)
	private BigDecimal unitPrice;

	public Product() {
		//Empty
	}

	public Product(long id, String productName, long supplierId, Category category, BigDecimal unitPrice) {
		this.id = id;
		this.productName = productName;
		this.supplierId = supplierId;
		this.category = category;
		this.unitPrice = unitPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		return Objects.hash(id, productName, supplierId, category, unitPrice);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return id == product.id && supplierId == product.supplierId && Objects.equals(productName, product.productName) && Objects.equals(category, product.category) && Objects.equals(unitPrice, product.unitPrice);
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", supplierId=" + supplierId +
				", category=" + category +
				", unitPrice=" + unitPrice +
				'}';
	}
}
