package com.cha104g1.freshtown_springboot.material.model.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.picking.model.model.PickingVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="material")
public class MaterialVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemNumber", updatable = false)
	private Integer itemNumber;
	
	@Column(name = "itemName")
	@NotEmpty(message="物料名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$", message = "物料名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間")
	private String itemName;
	
	@ManyToOne
	@JoinColumn(name="itemClassId",referencedColumnName ="itemClassId")
	@NotEmpty(message="物料類別分類: 請勿空白")
	private ItemsClassVO itemsClassVO;
	
	@Column(name = "stockQuantity")
	@NotNull(message="庫存數量: 請勿空白")
	private Integer stockQuantity;
	
	@Column(name = "quantityNot")
	@NotNull(message="未入庫數量: 請勿空白")
	private Integer quantityNot;
	
	@Column(name = "itemUnit", length=3)
	@NotEmpty(message="物料單位: 請勿空白")
	private String itemUnit;
	
	@Column(name = "safetyStock")
	@NotNull(message="安全庫存: 請勿空白")
	private Integer safetyStock;
	
	@Column(name = "itemStatus")
	@NotNull(message="物料狀態: 請勿空白")
	private Integer itemStatus;
	
	@Column(name = "purDate", nullable = true)
	private Date purDate;
	
	@ManyToOne
	@JoinColumn(name="storeId",referencedColumnName ="storeId")
	private StoresVO storesVO;
	
	@OneToMany(mappedBy = "materialVO", fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@OrderBy("pickingNo asc")
	private Set<PickingVO> pickingVO;

	
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

	public Set<PickingVO> getPickingVO() {
		return pickingVO;
	}

	public void setPickingVO(Set<PickingVO> pickingVO) {
		this.pickingVO = pickingVO;
	}

	public ItemsClassVO getItemsClass() {
		return itemsClassVO;
	}
	
	public void setItemsClass(ItemsClassVO itemsClassVO) {
		this.itemsClassVO = itemsClassVO;
	}
	
	public StoresVO getStoresVO() {
		return storesVO;
	}
	
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}

}
