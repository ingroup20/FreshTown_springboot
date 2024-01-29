package com.cha104g1.freshtown_springboot.orderdetail.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;



@Entity
@Table(name = "order_detail")
public class OrderDetailVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "orderDtlNo", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer	orderDtlNo;
	
	//
	@ManyToOne
	@JoinColumn(name="mealNo",referencedColumnName="mealNo")
//	@NotEmpty(message="商品編號: 請勿空白")
	private MealsVO mealsVO;
	
	@Column(name="mealQty")
//	@NotEmpty(message="商品數量: 請勿空白")
	private Integer	mealQty;
	
	//
	@ManyToOne
	@JoinColumn(name="orderId", referencedColumnName="orderId")
//	@NotEmpty(message="訂單編號: 請勿空白")
	private OrdersVO ordersVO;
	
	@Column(name="priceBought")
//	@NotEmpty(message="購買價格: 請勿空白")
	private Integer	priceBought;
	
	
	
	public Integer getOrderDtlNo() {
		return orderDtlNo;
	}
	public void setOrderDtlNo(Integer orderDtlNo) {
		this.orderDtlNo = orderDtlNo;
	}
	
	//
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
	
	//
	public void setOrdersVO(OrdersVO ordersVO) {
		this.ordersVO = ordersVO;
	}
	public OrdersVO getOrdersVO() {
		return ordersVO;
	}

	
	public Integer getPriceBought() {
		return priceBought;
	}
	public void setPriceBought(Integer priceBought) {
		this.priceBought = priceBought;
	}
	
	//
//	@OneToMany(mappedBy="orderDetailVO" ,cascade=CascadeType.ALL)
//	private CustomizedOrderVO customizedOrderVO;
//
//
//
//	public CustomizedOrderVO getCustomizedOrderVO() {
//		return customizedOrderVO;
//	}
//	public void setCustomizedOrderVO(CustomizedOrderVO customizedOrderVO) {
//		this.customizedOrderVO = customizedOrderVO;
//	}
	
	

}
