package com.cha104g1.freshtown_springboot.material.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.itemsclass.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;


@Entity
@Table(name="material")
public class MaterialVO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemNumber", updatable = false)
	private Integer itemNumber;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemClassId")
	private Integer itemClassId;
	
	@Column(name = "stockQuantity")
	private Integer stockQuantity;
	
	@Column(name = "quantityNot")
	private Integer quantityNot;
	
	@Column(name = "itemUnit", length=3)
	private String itemUnit;
	
	@Column(name = "safetyStock")
	private Integer safetyStock;
	
	@Column(name = "itemStatus")
	private Integer itemStatus;
	
	@Column(name = "purDate", nullable = true)
	private Date purDate;
	
	@Column(name = "storeId")
	private Integer storeId;
	
	@OneToMany(mappedBy = "material", cascade= CascadeType.ALL)
	@OrderBy("pickingNo asc")
	private Set<PickingVO> pickings;
	
//	@OneToMany(mappedBy = "material")
//	@OrderBy("id asc")
//	private Set<SupOrderVO> id;
	
	private ItemsClassVO itemsClass;
	
	public MaterialVO() {
		super();
	}

	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(Integer itemClassId) {
		this.itemClassId = itemClassId;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Integer getQuantityNot() {
		return quantityNot;
	}

	public void setQuantityNot(Integer quantityNot) {
		this.quantityNot = quantityNot;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public Integer getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(Integer safetyStock) {
		this.safetyStock = safetyStock;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public Date getPurDate() {
		return purDate;
	}

	public void setPurDate(Date purDate) {
		this.purDate = purDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Set<PickingVO> getPickings() {
		return pickings;
	}

	public void setPickings(Set<PickingVO> pickings) {
		this.pickings = pickings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialVO other = (MaterialVO) obj;
		return Objects.equals(itemNumber, other.itemNumber);
	}

	public ItemsClassVO getItemsClass() {
		return itemsClass;
	}

	public void setItemsClass(ItemsClassVO itemsClass) {
		this.itemsClass = itemsClass;
	}

//	public Set<SupOrderVO> getId() {
//		return id;
//	}
//
//	public void setId(Set<SupOrderVO> id) {
//		this.id = id;
//	}
       
    
	
}
