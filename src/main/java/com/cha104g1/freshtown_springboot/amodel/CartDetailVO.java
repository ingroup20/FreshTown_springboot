package com.cha104g1.freshtown_springboot.amodel;

import java.util.List;

import javax.persistence.ManyToOne;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

public class CartDetailVO {
	private Integer id;
	private MealsVO mealsVO;
	private Integer mealQty;
	private Integer qtyPrice;
	private List<CustomizedDetailVO> customizedDetailList;
	private Integer storeId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MealsVO getMealsVO() {
		return mealsVO;
	}
	public void setMealsVO(MealsVO mealsVO) {
		this.mealsVO = mealsVO;
	}
	public Integer getMealQty() {
		return mealQty;
	}
	public void setMealQty(Integer mealQty) {
		this.mealQty = mealQty;
	}
	public Integer getQtyPrice() {
		return qtyPrice;
	}
	public void setQtyPrice(Integer qtyPrice) {
		this.qtyPrice = qtyPrice;
	}

	public List<CustomizedDetailVO> getCustomizedDetailList() {
		return customizedDetailList;
	}
	public void setCustomizedDetailList(List<CustomizedDetailVO> customizedDetailList) {
		this.customizedDetailList = customizedDetailList;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
}
	
	
	