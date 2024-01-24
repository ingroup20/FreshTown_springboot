package com.cha104g1.freshtown_springboot.likestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@Entity
@Table(name = "like_store")
public class LikeStoreVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer	id;
	
	//
	@ManyToOne
	@JoinColumn(name = "customerId", referencedColumnName="customerId")
	@NotEmpty(message="會員流水號: 請勿空白")
	private CustomerVO customerVO;

	//
	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName="storeId")
	@NotEmpty(message="店家流水號: 請勿空白")
	private StoresVO storesVO;

	@Column(name="likeUnlike")
	private String	likeUnlike;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLikeUnlike() {
		return likeUnlike;
	}
	public void setLikeUnlike(String likeUnlike) {
		this.likeUnlike = likeUnlike;
	}
	
	
	//
	public StoresVO getStoresVO() {
		return storesVO;
	}
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	//
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	
	

}
