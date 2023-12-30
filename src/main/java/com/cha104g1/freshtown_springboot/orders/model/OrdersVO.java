package com.cha104g1.freshtown_springboot.orders.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@Entity
@Table(name = "orders")
public class OrdersVO {
	@Id
	@Column(name = "orderId", updatable = false)
	private Integer orderId;
	
	@Column(name="orderState")
	private Integer orderState;
	
	@Column(name="orderTime")
	private Timestamp orderTime;
	
	@Column(name="doneTime")
	private Timestamp doneTime;
	
	@Column(name="finishTime")
	private Timestamp finishTime;
	
	@Column(name="delayTime")
	private Timestamp delayTime;
	
	//
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	private CustomerVO customerVO;

	@Column(name="totalPrice")
	private Integer totalPrice;
	
	//
	@ManyToOne
	@JoinColumn(name="storeId", referencedColumnName="storeId")
	private StoresVO storesVO;
	
	@Column(name="delayDesc")
	private String delayDesc;
	
	@Column(name="comtVal")
	private Integer comtVal;
	
	@Column(name="comtCont")
	private String comtCont;
	
	@Column(name="comtTime")
	private Timestamp comtTime;
	
	@Column(name="remitDate")
	private Timestamp remitDate;
	
	@Column(name="remitState")
	private String remitState;
	
	@Column(name="payDate")
	private Timestamp payDate;
	
	@Column(name="payMethod")
	private Integer payMethod;
	
	@Column(name="payState")
	private Integer payState;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Timestamp getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Timestamp doneTime) {
		this.doneTime = doneTime;
	}
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
	public Timestamp getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Timestamp delayTime) {
		this.delayTime = delayTime;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDelayDesc() {
		return delayDesc;
	}
	public void setDelayDesc(String delayDesc) {
		this.delayDesc = delayDesc;
	}
	public Integer getComtVal() {
		return comtVal;
	}
	public void setComtVal(Integer comtVal) {
		this.comtVal = comtVal;
	}
	public String getComtCont() {
		return comtCont;
	}
	public void setComtCont(String comtCont) {
		this.comtCont = comtCont;
	}
	public Timestamp getComtTime() {
		return comtTime;
	}
	public void setComtTime(Timestamp comtTime) {
		this.comtTime = comtTime;
	}
	public Timestamp getRemitDate() {
		return remitDate;
	}
	public void setRemitDate(Timestamp remitDate) {
		this.remitDate = remitDate;
	}
	public String getRemitState() {
		return remitState;
	}
	public void setRemitState(String remitState) {
		this.remitState = remitState;
	}
	public Timestamp getPayDate() {
		return payDate;
	}
	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	
	//
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	//
	public StoresVO getStoresVO() {
		return storesVO;
	}
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	
	@OneToMany(mappedBy="ordersVO", cascade= CascadeType.ALL)
	private Set<OrderDetailVO> orderDetailVO;


	public Set<OrderDetailVO> getOrderDetailVO() {
		return orderDetailVO;
	}
	public void setOrderDetailVO(Set<OrderDetailVO> orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
	}
	
	
	@OneToMany(mappedBy="ordersVO",cascade= CascadeType.ALL)
	private Set<RefundsVO> refundsVO;


	public Set<RefundsVO> getRefundsVO() {
		return refundsVO;
	}
	public void setRefundsVO(Set<RefundsVO> refundsVO) {
		this.refundsVO = refundsVO;
	}
	
	
	
}
