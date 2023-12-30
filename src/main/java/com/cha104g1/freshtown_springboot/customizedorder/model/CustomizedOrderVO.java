package com.cha104g1.freshtown_springboot.customizedorder.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;



@Table(name="customized_order")
public class CustomizedOrderVO {

	@Id
	@Column(name="custedOrderNo")
	private Integer custedOrderNo;
	
	//
	@ManyToOne
	@JoinColumn(name="orderDtlNo",referencedColumnName="orderDtlNo")
	private OrderDetailVO orderDetailVO;
	//
	@ManyToOne
	@JoinColumn(name="custedDtlNo",referencedColumnName="custedDtlNo")
	private CustomizedDetailVO customizedDetailVO;

	
	
	public Integer getCustedOrderNo() {
		return custedOrderNo;
	}
	public void setCustedOrderNo(Integer custedOrderNo) {
		this.custedOrderNo = custedOrderNo;
	}
	
	//
	public OrderDetailVO getOrderDetailVO() {
		return orderDetailVO;
	}
	public void setOrderDetailVO(OrderDetailVO orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
	}
	
	//
	public CustomizedDetailVO getCustomizedDetailVO() {
		return customizedDetailVO;
	}
	public void setCustomizedDetailVO(CustomizedDetailVO customizedDetailVO) {
		this.customizedDetailVO = customizedDetailVO;
	}
	
	
	
	
	
}
