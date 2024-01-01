package com.cha104g1.freshtown_springboot.orders.model;

import java.sql.Timestamp;
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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@Entity
@Table(name = "orders")
public class OrdersVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "orderId", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer orderId;
	
	@Column(name="orderState")
	@NotEmpty(message="訂餐狀態: 請勿空白")
	@Pattern(regexp = "^[(0-5)]$", message = "訂餐狀態: 只能是數字(0未成立 1新訂單待確認 2製作中 3已出餐待取 4結單 5 取消訂單) ")
	private Integer orderState;
	
	@Column(name="orderTime")
	@NotEmpty(message="下單(預約)時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp orderTime;
	
	@Column(name="doneTime")
	@NotEmpty(message="出餐時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp doneTime;
	
	@Column(name="finishTime")
	@NotEmpty(message="完成時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp finishTime;
	
	@Column(name="delayTime")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	@NotEmpty(message="超時紀錄時間: 請勿空白")
	
	private Timestamp delayTime;
	
	//
	@ManyToOne
	@JoinColumn(name="customerId", referencedColumnName="customerId")
	@NotEmpty(message="會員流水號: 請勿空白")
	private CustomerVO customerVO;

	@Column(name="totalPrice")
	@NotEmpty(message="總金額: 請勿空白")
	private Integer totalPrice;
	
	//
	@ManyToOne
	@JoinColumn(name="storeId", referencedColumnName="storeId")
	@NotEmpty(message="店家流水號: 請勿空白")
	private StoresVO storesVO;
	
	@Column(name="delayDesc")
	@Size(min=0,max=255,message="超時/取消訂單備註超過字數限制")
	private String delayDesc;
	
	@Column(name="comtVal")
	@Pattern(regexp = "^[(0-5)]$", message = "評分: 只能是0到5之間")
	private Integer comtVal;
	
	@Column(name="comtCont")
	@Size(min=0,max=255,message="評論超過字數限制")
	private String comtCont;
	
	@Column(name="comtTime")//評論日期
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp comtTime;
	
	@Column(name="remitDate")//匯款日期
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp remitDate;
	
	@Column(name="remitState")
	@NotEmpty(message="匯款狀態: 請勿空白")
	@Pattern(regexp = "^[(NY)]$", message = "匯款狀態: 只能是英文字母 (N:未匯款 Y:已匯款)")
	private String remitState;
	
	@Column(name="payDate")
	@NotEmpty(message="線上付款時間: 請勿空白")
//	@Future(message="日期必須是在今日(不含)之後")
//	@Past(message="日期必須是在今日(含)之前")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Timestamp payDate;
	
	@Column(name="payMethod")
	@NotEmpty(message="付款方式: 請勿空白")
	@Pattern(regexp = "^[(1)]$", message = "付款方式: 只能是數字 (1:刷卡 )")
	private Byte payMethod;
	
	@Column(name="payState")
	@NotEmpty(message="付款狀態: 請勿空白")
	@Pattern(regexp = "^[(012)]$", message = "付款狀態: 只能是數字(0未付款 1已付款 2退款)  ")
	private Byte payState;
	
	
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
	public Byte getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Byte payMethod) {
		this.payMethod = payMethod;
	}
	public Byte getPayState() {
		return payState;
	}
	public void setPayState(Byte payState) {
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
