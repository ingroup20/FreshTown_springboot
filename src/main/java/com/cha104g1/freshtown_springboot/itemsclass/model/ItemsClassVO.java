package com.cha104g1.freshtown_springboot.itemsclass.model;


import java.io.Serializable;
import java.util.Objects;
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

import com.cha104g1.freshtown_springboot.material.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name= "items_class")
public class ItemsClassVO implements Serializable{
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="itemClassId", updatable= false)
      private Integer itemClassId;
	  
	  @Column(name="itemClassName", length=10)
      private String itemClassName;
	  
	  @ManyToOne
	  @JoinColumn(name="storeId",referencedColumnName ="storeId")
      private StoresVO storesVO;
	  
	  @OneToMany(mappedBy = "itemsClassVO", cascade= CascadeType.ALL)
	  @OrderBy("itemNumber asc")
	  private Set<MaterialVO> materials;
      
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


	public Set<MaterialVO> getMaterials() {
		return materials;
	}

	public void setMaterials(Set<MaterialVO> materials) {
		this.materials = materials;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemClassId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemsClassVO other = (ItemsClassVO) obj;
		return Objects.equals(itemClassId, other.itemClassId);
	}
	//
	public StoresVO getStoresVO() {
		return storesVO;
	}
	//
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	
	
	
      
}
