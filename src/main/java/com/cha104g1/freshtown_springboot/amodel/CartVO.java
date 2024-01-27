package com.cha104g1.freshtown_springboot.amodel;

import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

public class CartVO {
	private Integer id;
	private MealsVO mealsVO;
	private OrderDetailVO orderDetailVO;
	private CustomizedOrderVO customizedOrderVO;
	private Integer customerId;
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
	public OrderDetailVO getOrderDetailVO() {
		return orderDetailVO;
	}
	public void setOrderDetailVO(OrderDetailVO orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
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
	public CustomizedOrderVO getCustomizedOrderVO() {
		return customizedOrderVO;
	}
	public void setCustomizedOrderVO(CustomizedOrderVO customizedOrderVO) {
		this.customizedOrderVO = customizedOrderVO;
	}

	
	
	
}
