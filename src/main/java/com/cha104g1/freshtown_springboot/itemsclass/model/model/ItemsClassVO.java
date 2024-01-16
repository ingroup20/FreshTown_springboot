package com.cha104g1.freshtown_springboot.itemsclass.model.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Entity
@Table(name = "items_class")
public class ItemsClassVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemClassId", updatable = false)
	private Integer itemClassId;

	@Column(name = "itemClassName", length = 10)
	@NotEmpty(message = "物料分類名稱: 請勿空白")
	@Pattern(regexp = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$", message = "物料名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間")
	private String itemClassName;

	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	private StoresVO storesVO;

	@OneToMany(mappedBy = "itemsClassVO", cascade = CascadeType.ALL)
	@OrderBy("itemNumber asc")
	private Set<MaterialVO> materialVO;

	public ItemsClassVO() {
		super();
	}

	public Integer getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(Integer itemClassId) {
		this.itemClassId = itemClassId;
	}

	public String getItemClassName() {
		return itemClassName;
	}

	public void setItemClassName(String itemClassName) {
		this.itemClassName = itemClassName;
	}

	public Set<MaterialVO> getMaterialVO() {
		return materialVO;
	}

	public void setMaterialVO(Set<MaterialVO> materialVO) {
		this.materialVO = materialVO;
	}

	public StoresVO getStoresVO() {
		return storesVO;
	}

	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}

}
