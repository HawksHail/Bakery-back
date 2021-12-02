package com.cts.capstone.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "supplierid")
	private Long id;

	@Column(name = "companyname", nullable = false, length = 40)
	@Length(max = 40, message = "Company name max length 40")
	private String companyName;

	@Column(name = "contactname", length = 30)
	@Length(max = 40, message = "Contact name max length 30")
	private String contactName;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
	private List<Product> productList;

	public Supplier() {
		//Empty
	}

	public Supplier(Long id, String companyName, String contactName) {
		this.id = id;
		this.companyName = companyName;
		this.contactName = contactName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, companyName, contactName, productList);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Supplier supplier = (Supplier) o;
		return Objects.equals(id, supplier.id) && Objects.equals(companyName, supplier.companyName) && Objects.equals(contactName, supplier.contactName) && Objects.equals(productList, supplier.productList);
	}

	@Override
	public String toString() {
		return "Supplier{" +
				"id=" + id +
				", companyName='" + companyName + '\'' +
				", contactName='" + contactName + '\'' +
				", productList=" + productList +
				'}';
	}
}
