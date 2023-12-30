package com.cha104g1.freshtown_springboot.supplier.model;


public class SupVO implements java.io.Serializable {
	private Integer supId;
	private String supplierName;
	private String supplierContact;
	private String supplierPhone;
	private Integer storeId;
	private Integer supplierState;

	public Integer getSupId() {
		return supId;
	}

	public void setSupId(Integer supId) {
		this.supId = supId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(Integer supplierState) {
		this.supplierState = supplierState;
	}

}
