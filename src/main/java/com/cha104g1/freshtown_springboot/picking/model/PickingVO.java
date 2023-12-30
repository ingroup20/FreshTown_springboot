package com.cha104g1.freshtown_springboot.picking.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.material.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="picking")
public class PickingVO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pickingNo", updatable= false)
	private Integer pickingNo;
	
	@Column(name="itemNumber")
	private Integer itemNumber;
	
	@Column(name="storeId")
	private Integer storeId;
	
	@Column(name="sEmpId")
	private Integer sEmpId;
	
	@Column(name="pickingQuantity")
	private Integer pickingQuantity;
	
	@Column(name="pickingUnit", nullable=true, length=3)
	private String pickingUnit;
	
	@Column(name="pickingStatus")
	private Integer pickingStatus;
	
	@Column(name="pickingClass")
	private Integer pickingClass;
	
	@Column(name="pickingDate")
	private Date pickingDate;
	
	@Column(name="marks", length=128)
	private String marks;
	
	@ManyToOne
	@JoinColumn(name = "itemNumber", referencedColumnName = "itemNumber")
	private MaterialVO material;
	
	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	private StoresVO stores;

	public PickingVO() {
		super();
	}

	public Integer getPickingNo() {
		return pickingNo;
	}

	public void setPickingNo(Integer pickingNo) {
		this.pickingNo = pickingNo;
	}

	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getSEmpId() {
		return sEmpId;
	}

	public void setSEmpId(Integer sEmpId) {
		this.sEmpId = sEmpId;
	}

	public Integer getPickingQuantity() {
		return pickingQuantity;
	}

	public void setPickingQuantity(Integer pickingQuantity) {
		this.pickingQuantity = pickingQuantity;
	}

	public String getPickingUnit() {
		return pickingUnit;
	}

	public void setPickingUnit(String pickingUnit) {
		this.pickingUnit = pickingUnit;
	}

	public Integer getPickingStatus() {
		return pickingStatus;
	}

	public void setPickingStatus(Integer pickingStatus) {
		this.pickingStatus = pickingStatus;
	}

	public Integer getPickingClass() {
		return pickingClass;
	}

	public void setPickingClass(Integer pickingClass) {
		this.pickingClass = pickingClass;
	}
	

	public Date getPickingDate() {
		return pickingDate;
	}

	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public MaterialVO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialVO material) {
		this.material = material;
	}

	public StoresVO getStores() {
		return stores;
	}

	public void setStores(StoresVO stores) {
		this.stores = stores;
	}
	
	
	

}
