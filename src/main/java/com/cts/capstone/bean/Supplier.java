package com.cts.capstone.bean;

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
	public String toString() {
		return "Suppliers{" +
				"supplier_id=" + supplierId +
				", company_name='" + companyName + '\'' +
				", contact_name='" + contactName + '\'' +
				'}';
	}
}
