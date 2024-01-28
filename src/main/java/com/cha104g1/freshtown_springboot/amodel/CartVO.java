package com.cha104g1.freshtown_springboot.amodel;

import java.util.List;

import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

public class CartVO {
	private Integer id;
	private Integer mealNo;
	private Integer mealQty;
	private String customizedOrderNoList;
	private Integer customerId;
	private Integer storeId;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getMealQty() {
		return mealQty;
	}
	public void setMealQty(Integer mealQty) {
		this.mealQty = mealQty;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getMealNo() {
		return mealNo;
	}
	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}
	public String getCustomizedOrderNoList() {
		return customizedOrderNoList;
	}
	public void setCustomizedOrderNoList(String customizedOrderNoList) {
		this.customizedOrderNoList = customizedOrderNoList;
	}
	
	
	
	
	
}
