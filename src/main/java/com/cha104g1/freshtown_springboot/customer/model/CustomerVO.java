package com.cha104g1.freshtown_springboot.customer.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.service.model.ServiceVO;

@Entity
@Table(name = "customer")
public class CustomerVO {
	
		@Id
		@Column(name = "customerId", updatable = false)
	    private Integer customerId;
	    private String customerPw;
	    private String customerMob;
	    private String mobChecked;
	    private String customerEmail;
	    private String customerNic;
	    private String customerAddress;
	    private Byte customerState;
	    

		public Integer getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}
		public String getCustomerPw() {
			return customerPw;
		}
		public void setCustomerPw(String customerPw) {
			this.customerPw = customerPw;
		}
		public String getCustomerMob() {
			return customerMob;
		}
		public void setCustomerMob(String customerMob) {
			this.customerMob = customerMob;
		}
		public String getMobChecked() {
			return mobChecked;
		}
		public void setMobChecked(String mobChecked) {
			this.mobChecked = mobChecked;
		}
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		public String getCustomerNic() {
			return customerNic;
		}
		public void setCustomerNic(String customerNic) {
			this.customerNic = customerNic;
		}
		public String getCustomerAddress() {
			return customerAddress;
		}
		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}
		public Byte getCustomerState() {
			return customerState;
		}
		public void setCustomerState(Byte customerState) {
			this.customerState = customerState;
		}
		
	    //
		@OneToMany(mappedBy="CustomerVO", cascade= CascadeType.ALL)
		private Set<OrdersVO> ordersVO;

		public Set<OrdersVO> getOrdersVO() {
			return ordersVO;
		}
		public void setOrdersVO(Set<OrdersVO> ordersVO) {
			this.ordersVO = ordersVO;
		}
		//
		@OneToMany(mappedBy="CustomerVO", cascade= CascadeType.ALL)
		private Set<ServiceVO> serviceVO;

		public Set<ServiceVO> getServiceVO() {
			return serviceVO;
		}
		public void setServiceVO(Set<ServiceVO> serviceVO) {
			this.serviceVO = serviceVO;
		}
		//
		@OneToMany(mappedBy="CustomerVO", cascade= CascadeType.ALL)
		private Set<LikeStoreVO> likeStoreVO;

		public Set<LikeStoreVO> getLikeStoreVO() {
			return likeStoreVO;
		}
		public void setLikeStoreVO(Set<LikeStoreVO> likeStoreVO) {
			this.likeStoreVO = likeStoreVO;
		}

}
