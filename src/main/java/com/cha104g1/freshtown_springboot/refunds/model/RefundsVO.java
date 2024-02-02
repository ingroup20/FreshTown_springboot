package com.cha104g1.freshtown_springboot.refunds.model;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;



@Entity
@Table(name = "refunds")
public class RefundsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	//延遲載入
	@OneToOne
	@JoinColumn(name="orderId", referencedColumnName="orderId")
//	@NotNull(message="訂單編號: 請勿空白")
//	@Pattern(regexp = "^(0-9)$", message = "訂單編號: 只能數字")
	private OrdersVO ordersVO;
//	@Column(name="orderId")
//	private Integer orderId;
	
	
	@Column(name="refundState")
	@NotEmpty(message="退款狀態: 請勿空白")
	@Pattern(regexp = "^[(YN)]{1}$", message = "退款狀態: 只能是(N未退款 Y已退款)")
	private String refundState;
	
	@Column(name="refundDollar")
	@NotNull(message="退款金額: 請勿空白")
	private Integer refundDollar;
	
	@Column(name="refundDate")
//	@NotNull(message="退款時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
//	@Temporal(TemporalType.TIMESTAMP)
	private Date refundDate;
	
	@Column(name="creationDate")
//	@NotNull(message="成立退款單時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
//	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	
	public RefundsVO() {
		
	}
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//
//	public OrdersVO getOrdersVO() {
//		return ordersVO;
//	}
//	public void setOrdersVO(OrdersVO ordersVO) {
//		this.ordersVO = ordersVO;
//	}
	
	public String getRefundState() {
		return refundState;
	}
//	public Integer getOrderId() {
//		return orderId;
//	}
	
	
//	public void setOrderId(Integer orderId) {
//		this.orderId = orderId;
//	}
	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
	public Integer getRefundDollar() {
		return refundDollar;
	}
	public void setRefundDollar(Integer refundDollar) {
		this.refundDollar = refundDollar;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public OrdersVO getOrdersVO() {
		return ordersVO;
	}

	public void setOrdersVO(OrdersVO ordersVO) {
		this.ordersVO = ordersVO;
	}
	
	
	
}
