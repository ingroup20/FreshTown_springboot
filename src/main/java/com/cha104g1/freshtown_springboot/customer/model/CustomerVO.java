package com.cha104g1.freshtown_springboot.customer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;

@Entity
@Table(name = "customer")
public class CustomerVO {
	
		
	    private Integer customerId;
	    private String customerPw;
	    private String customerMob;
	    private String mobChecked;
	    private String customerEmail;
	    private String customerNic;
	    private String customerAddress;
	    private Byte customerState;
	    
	    @Id
	    @Column(name = "customerId")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Integer getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}
		
		@Column(name = "customerPw")
		public String getCustomerPw() {
			return customerPw;
		}
		public void setCustomerPw(String customerPw) {
			this.customerPw = customerPw;
		}
		
		@Column(name = "customerMob")
		public String getCustomerMob() {
			return customerMob;
		}
		public void setCustomerMob(String customerMob) {
			this.customerMob = customerMob;
		}
		
		@Column(name = "mobChecked")
		public String getMobChecked() {
			return mobChecked;
		}
		public void setMobChecked(String mobChecked) {
			this.mobChecked = mobChecked;
		}
		
		@Column(name = "customerEmail")
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		
		@Column(name = "customerNic")
		public String getCustomerNic() {
			return customerNic;
		}
		public void setCustomerNic(String customerNic) {
			this.customerNic = customerNic;
		}
		
		@Column(name = "customerAddress")
		public String getCustomerAddress() {
			return customerAddress;
		}
		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}
		
		@Column(name = "customerState")
		public Byte getCustomerState() {
			return customerState;
		}
		public void setCustomerState(Byte customerState) {
			this.customerState = customerState;
		}
		
	    //
		private Set<OrdersVO> ordersVO;

		@OneToMany(mappedBy="customerVO", cascade= CascadeType.ALL)
		public Set<OrdersVO> getOrdersVO() {
			return ordersVO;
		}
		public void setOrdersVO(Set<OrdersVO> ordersVO) {
			this.ordersVO = ordersVO;
		}
		//
		
		private Set<ServiceVO> serviceVO;
        
		@OneToMany(mappedBy="customerVO", cascade= CascadeType.ALL)
		public Set<ServiceVO> getServiceVO() {
			return serviceVO;
		}
		public void setServiceVO(Set<ServiceVO> serviceVO) {
			this.serviceVO = serviceVO;
		}
		//
		
		private Set<LikeStoreVO> likeStoreVO;

		@OneToMany(mappedBy="customerVO", cascade= CascadeType.ALL)
		public Set<LikeStoreVO> getLikeStoreVO() {
			return likeStoreVO;
		}
		public void setLikeStoreVO(Set<LikeStoreVO> likeStoreVO) {
			this.likeStoreVO = likeStoreVO;
		}

	

}
