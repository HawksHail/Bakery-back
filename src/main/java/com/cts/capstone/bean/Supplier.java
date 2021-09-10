package com.cts.capstone.bean;

import java.util.Objects;

public class Supplier {
	private long supplierId;
	private String companyName;
	private String contactName;

	public Supplier() {
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
		return "Suppliers{" +
				"supplier_id=" + supplierId +
				", company_name='" + companyName + '\'' +
				", contact_name='" + contactName + '\'' +
				'}';
	}
}
