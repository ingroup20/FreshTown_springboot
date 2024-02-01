package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Entity
@Table(name="sup_order")
public class SupOrderVO implements java.io.Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="supId",referencedColumnName ="supId")
	private SupVO supVO;
	
	@ManyToOne
	@JoinColumn(name="purNo",referencedColumnName ="itemNumber")
	private MaterialVO materialVO;
	
	private Integer amount;
	private Integer unitPrice;
	private Date purDate;
	private Date preDate;
	private Integer oStatus;
	private Date deliDate;
	private String marks;
	
	@ManyToOne
	@JoinColumn(name="storeId",referencedColumnName ="storeId")
    private StoresVO storesVO;
	
	public StoresVO getStoresVO() {
		return storesVO;
	}
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	public MaterialVO getMaterialVO() {
		return materialVO;
	}
	public void setMaterialVO(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}
	public SupVO getSupVO() {
		return supVO;
	}
	public void setSupVO(SupVO supVO) {
		this.supVO = supVO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public Integer getSupId() {
//		return supId;
//	}
//	public void setSupId(Integer supId) {
//		this.supId = supId;
//	}
//	public Integer getPurNo() {
//		return purNo;
//	}
//	public void setPurNo(Integer purNo) {
//		this.purNo = purNo;
//	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Date getPurDate() {
		return purDate;
	}
	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}
	public Date getPreDate() {
		return preDate;
	}
	public void setPreDate(Date preDate) {
		this.preDate = preDate;
	}
	public Integer getoStatus() {
		return oStatus;
	}
	public void setoStatus(Integer oStatus) {
		this.oStatus = oStatus;
	}
	public Date getDeliDate() {
		return deliDate;
	}
	public void setDeliDate(Date deliDate) {
		this.deliDate = deliDate;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}	

}