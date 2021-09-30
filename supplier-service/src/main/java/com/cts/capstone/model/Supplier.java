package com.cts.capstone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@Column(name = "supplierid")
	private long supplierId;

	@Column(name = "companyname", nullable = false, length = 40)
	private String companyName;

	@Column(name = "contactname", length = 30)
	private String contactName;

	public Supplier() {
		//Empty
	}

	public Supplier(long supplierId, String companyName, String contactName) {
		this.supplierId = supplierId;
		this.companyName = companyName;
		this.contactName = contactName;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
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

	@Override
	public int hashCode() {
		return Objects.hash(supplierId, companyName, contactName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Supplier supplier = (Supplier) o;
		return supplierId == supplier.supplierId && Objects.equals(companyName, supplier.companyName) && Objects.equals(contactName, supplier.contactName);
	}

	@Override
	public String toString() {
		return "Supplier{" +
				"supplierId=" + supplierId +
				", companyName='" + companyName + '\'' +
				", contactName='" + contactName + '\'' +
				'}';
	}
}
