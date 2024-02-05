package com.cha104g1.freshtown_springboot.supplier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Entity
@Table(name="supplier")
public class SupVO implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer supId;
	
	@NotEmpty(message="供應商名稱不能空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$", message = "供應商名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間")
	private String supplierName;
	
	@NotEmpty(message="聯絡人名稱不能空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$", message = "聯絡人名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間")
	private String supplierContact;
	
	@NotNull(message="電話不能空白")
	private String supplierPhone;
	
	@ManyToOne
	@JoinColumn(name="storeId",referencedColumnName ="storeId")
	private StoresVO storesVO;
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

	public StoresVO getStoresVO() {
		return storesVO;
	}
	//
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}

	public Integer getSupplierState() {
		return supplierState;
	}

	public void setSupplierState(Integer supplierState) {
		this.supplierState = supplierState;
	}

	@Override
	public String toString() {
		return "SupVO [supId=" + supId + ", supplierName=" + supplierName + ", supplierContact=" + supplierContact
				+ ", supplierPhone=" + supplierPhone + ", storesVO=" + storesVO + ", supplierState=" + supplierState
				+ "]";
	}

//	public Integer getStoreId() {
//		return storeId;
//	}
//
//	public void setStoreId(Integer storeId) {
//		this.storeId = storeId;
//	}

}
