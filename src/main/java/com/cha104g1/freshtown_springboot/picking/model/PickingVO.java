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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="picking")
public class PickingVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pickingNo;
	private MaterialVO materialVO;
	private StoresVO storesVO;
	private StoreEmpVO storeEmpVO;
	private Integer pickingQuantity;
	private String pickingUnit;
	private Integer pickingStatus;
	private Integer pickingClass;
	private Date pickingDate;
	private String marks;
	
	

	public PickingVO() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pickingNo", updatable= false)
	public Integer getPickingNo() {
		return this.pickingNo;
	}

	public void setPickingNo(Integer pickingNo) {
		this.pickingNo = pickingNo;
	}
	@Column(name="pickingQuantity")
	@NotNull(message = "領料數量: 請勿空白")
	public Integer getPickingQuantity() {
		return pickingQuantity;
	}

	public void setPickingQuantity(Integer pickingQuantity) {
		this.pickingQuantity = pickingQuantity;
	}
	@Column(name="pickingUnit", nullable=true, length=3)
	@NotEmpty(message = "領料單位: 請勿空白")
	public String getPickingUnit() {
		return pickingUnit;
	}

	public void setPickingUnit(String pickingUnit) {
		this.pickingUnit = pickingUnit;
	}
	@Column(name="pickingStatus")
	@NotNull(message="領料狀態: 請勿空白")
	@Min(value=0, message = "領料狀態: 只能是數字(0:審核中 1:已領取 )  DEFAULT 0")
	@Max(value=1, message = "領料狀態: 只能是數字(0:審核中 1:已領取 )  DEFAULT 0")
//	@Pattern(regexp = "^[0-1]$", message = "領料狀態: 只能是數字(0:審核中, 1已領取)")
	public Integer getPickingStatus() {
		return pickingStatus;
	}

	public void setPickingStatus(Integer pickingStatus) {
		this.pickingStatus = pickingStatus;
	}
	@Column(name="pickingClass")
	@NotNull(message="領料類型: 請勿空白")
	@Min(value=0, message = "領料類型: 只能是數字(0:一般領料 1:報廢 )")
	@Max(value=1, message = "領料類型: 只能是數字(0:一般領料 1:報廢 )")
	public Integer getPickingClass() {
		return pickingClass;
	}

	public void setPickingClass(Integer pickingClass) {
		this.pickingClass = pickingClass;
	}
	
	@Column(name="pickingDate")
	@NotNull(message="領料日期: 請勿空白")
	public Date getPickingDate() {
		return pickingDate;
	}

	public void setPickingDate(Date pickingDate) {
		this.pickingDate = pickingDate;
	}
	@Column(name="marks")
	@Size(min=0,max=128,message="備註: 長度必需在小於128")
	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}
    @ManyToOne
	@JoinColumn(name = "itemNumber")
	public MaterialVO getMaterialVO() {
		return this.materialVO;
	}

	public void setMaterialVO(MaterialVO materialVO) {
		this.materialVO = materialVO;
	}
	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	public StoresVO getStoresVO() {
		return storesVO;
	}
	
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	@ManyToOne
	@JoinColumn(name = "sEmpId", referencedColumnName = "sEmpId")
	public StoreEmpVO getStoreEmpVO() {
		return storeEmpVO;
	}
	
	public void setStoreEmpVO(StoreEmpVO storeEmpVO) {
		this.storeEmpVO = storeEmpVO;
	}
	
}
