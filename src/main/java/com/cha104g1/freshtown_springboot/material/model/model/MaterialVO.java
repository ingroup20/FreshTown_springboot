package com.cha104g1.freshtown_springboot.material.model.model;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Date;
import java.util.HashSet;
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
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.material.model.service.UniqueItemNameValidator;



@Entity
@Table(name="material")
public class MaterialVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemNumber", updatable = false)
	private Integer itemNumber;
	
	@Column(name = "itemName",  unique = true)
	@NotEmpty(message="物料名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$", message = "物料名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間")
	@UniqueItemName
	private String itemName;
	
	@ManyToOne
	@JoinColumn(name="itemClassId", referencedColumnName = "itemClassId")
	@NotNull(message="物料分類編號: 請勿空白")
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
	@Min(value=0, message = "領料狀態: 只能是數字(0:低於安全庫存 1:數量足夠 2:作廢 )")
	@Max(value=2, message = "領料狀態: 只能是數字(0:低於安全庫存 1:數量足夠 2:作廢)")
//	@Pattern(regexp = "^[012]$", message = "物料狀態: 只能是數字(0: 低於安全庫存 1: 數量足夠 2:作廢")
	private Integer itemStatus;
	
	@Column(name = "purDate")
	@NotNull(message="日期: 請勿空白")
	private Date purDate;
	
	@ManyToOne
	@JoinColumn(name="storeId",referencedColumnName ="storeId")
	private StoresVO storesVO;
	
//	private StoreEmpVO storeEmpVO;
	
	
	@OneToMany(mappedBy = "materialVO",cascade= CascadeType.ALL)
//	@OrderBy("pickingNo asc")
	private Set<PickingVO> pickingVO = new HashSet<PickingVO>();
	
	public Set<PickingVO> getPickingVO() {
		return this.pickingVO;
	}
	public void setPickingVO(Set<PickingVO> pickingVO) {
		this.pickingVO = pickingVO;
	}
	

	
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


	public ItemsClassVO getItemsClassVO() {
		return itemsClassVO;
	}

	public void setItemsClassVO(ItemsClassVO itemsClassVO) {
		this.itemsClassVO = itemsClassVO;
	}

	public StoresVO getStoresVO() {
		return storesVO;
	}
	
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	@Constraint(validatedBy = UniqueItemNameValidator.class)
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface UniqueItemName {
	    String message() default "此物料名稱已有建立";
	    Class<?>[] groups() default { };
	    Class<? extends Payload>[] payload() default { };
	}

//	public StoreEmpVO getStoreEmpVO() {
//		return storeEmpVO;
//	}
//	public void setStoreEmpVO(StoreEmpVO storeEmpVO) {
//		this.storeEmpVO = storeEmpVO;
//	}
	
	

}
