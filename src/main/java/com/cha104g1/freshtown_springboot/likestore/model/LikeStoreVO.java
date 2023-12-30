package com.cha104g1.freshtown_springboot.likestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@Entity
@Table(name = "like_store")
public class LikeStoreVO {
	@Id
	@Column(name = "id", updatable = false)
	private Integer	id;
	
//	@JoinColumn(name = "customerId", referencedColumnName="customerId")
//	private CustomerVO customer;
		@Column(name="customerId")
		private Integer	customerId;
	
	@JoinColumn(name = "storeId", referencedColumnName="storeId")
	private StoresVO storesVO;
//		@Column(name="storeId")
//		private Integer	storeId;
	
	
	@Column(name="likeUnlike")
	private String	likeUnlike;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
//	public Integer getStoreId() {
//		return storeId;
//	}
//	public void setStoreId(Integer storeId) {
//		this.storeId = storeId;
//	}
	public String getLikeUnlike() {
		return likeUnlike;
	}
	public void setLikeUnlike(String likeUnlike) {
		this.likeUnlike = likeUnlike;
	}
	
	
	//聯合印設
	public StoresVO getStoresVO() {
		return storesVO;
	}
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	
	

}
