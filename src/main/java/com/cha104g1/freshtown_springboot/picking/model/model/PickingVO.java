package com.cha104g1.freshtown_springboot.picking.model.model;

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
import javax.validation.constraints.NotEmpty;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="picking")
public class PickingVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pickingNo", updatable= false)
	private Integer pickingNo;
	
	
	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	private StoresVO storesVO;
	
	@ManyToOne
	@JoinColumn(name = "sEmpId", referencedColumnName = "sEmpId")
	private StoreEmpVO storeEmpVO;
	
	@Column(name="pickingQuantity")
	@NotEmpty(message = "領料數量: 請勿空白")
	private Integer pickingQuantity;
	
	@Column(name="pickingUnit", nullable=true, length=3)
	@NotEmpty(message = "領料單位: 請勿空白")
	private String pickingUnit;
	
	@Column(name="pickingStatus")
	@NotEmpty(message="領料狀態: 請勿空白")
	private Integer pickingStatus;
	
	@Column(name="pickingClass")
	@NotEmpty(message="領料類型: 請勿空白")
	private Integer pickingClass;
	
	@Column(name="pickingDate")
	private Date pickingDate;
	
	@Column(name="marks", length=128)
	private String marks;
	
	@ManyToOne
	@JoinColumn(name = "itemNumber", referencedColumnName = "itemNumber")
	private MaterialVO materialVO;
	

	public PickingVO() {
		super();
	}

	public Integer getPickingNo() {
		return pickingNo;
	}

	public void setPickingNo(Integer pickingNo) {
		this.pickingNo = pickingNo;
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
	
	public MaterialVO getMaterialVO() {
		return materialVO;
	}
	
	public void setMaterial(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}
	
	public StoresVO getStoresVO() {
		return storesVO;
	}
	
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	public StoreEmpVO getStoreEmpVO() {
		return storeEmpVO;
	}
	//
	public void setStoreEmpVO(StoreEmpVO storeEmpVO) {
		this.storeEmpVO = storeEmpVO;
	}
	
}
