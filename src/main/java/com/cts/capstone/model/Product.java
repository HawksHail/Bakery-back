package com.cts.capstone.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productid")
	private Long id;

	@Column(name = "productname", nullable = false, length = 40)
	@Length(max = 40, message = "Product name max length 40")
	private String productName;

	@ManyToOne
	@JoinColumn(name = "supplierid")
	@JsonIncludeProperties({"id", "companyName"})
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	@JsonIncludeProperties({"id", "categoryName"})
	private Category category;

	@Column(name = "unitprice", precision = 7, scale = 2)
	private BigDecimal unitPrice;

	@Column(name = "img_url")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String imgURL;

	@Column(name = "img_credit")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String imgCredit;

	public Product() {
		//Empty
	}

	public Product(Long id, String productName, Supplier supplier, Category category, BigDecimal unitPrice) {
		this.id = id;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.unitPrice = unitPrice;
		this.imgURL = null;
		this.imgCredit = null;
	}

	public Product(Long id, String productName, Supplier supplier, Category category, BigDecimal unitPrice, String imgURL, String imgCredit) {
		this.id = id;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.unitPrice = unitPrice;
		this.imgURL = imgURL;
		this.imgCredit = imgCredit;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getImgCredit() {
		return imgCredit;
	}

	public void setImgCredit(String imgCredit) {
		this.imgCredit = imgCredit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productName, supplier, category, unitPrice, imgURL, imgCredit);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(supplier, product.supplier) && Objects.equals(category, product.category) && Objects.equals(unitPrice, product.unitPrice) && Objects.equals(imgURL, product.imgURL) && Objects.equals(imgCredit, product.imgCredit);
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", productName='" + productName + '\'' +
				", supplier=" + supplier +
				", category=" + category +
				", unitPrice=" + unitPrice +
				", imgURL='" + imgURL + '\'' +
				", imgCredit='" + imgCredit + '\'' +
				'}';
	}
}
