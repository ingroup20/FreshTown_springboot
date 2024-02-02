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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	    private String customerAccount;
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
		@NotEmpty(message="會員密碼: 請勿空白")
		@Pattern(regexp = "^[a-zA-Z0-9_]{3,18}$", message = "密碼為3~18位大小寫英數字")
		public String getCustomerPw() {
			return customerPw;
		}
		public void setCustomerPw(String customerPw) {
			this.customerPw = customerPw;
		}
		
		@Column(name = "customerMob")
		@NotEmpty(message="手機號碼: 請勿空白")
		@Pattern(regexp="09\\d{8}")
	    @Size(min = 10, max = 10)
		public String getCustomerMob() {
			return customerMob;
		}
		public void setCustomerMob(String customerMob) {
			this.customerMob = customerMob;
		}
		
		@Column(name = "mobChecked")
		@NotEmpty(message="手機認證: 請勿空白")
		public String getMobChecked() {
			return mobChecked;
		}
		public void setMobChecked(String mobChecked) {
			this.mobChecked = mobChecked;
		}
		
		@Column(name = "customerEmail")
		@NotEmpty(message="會員信箱: 請勿空白")
		@Size(max = 100)
	    @Email
		public String getCustomerEmail() {
			return customerEmail;
		}
		public void setCustomerEmail(String customerEmail) {
			this.customerEmail = customerEmail;
		}
		
		@Column(name = "customerNic")
		@NotEmpty(message="會員暱稱: 請勿空白")
		@Pattern(regexp = "^[a-zA-Z\u4E00-\u9FFF_]+$", message = "暱稱只能包含繁體中文或英文")
		public String getCustomerNic() {
			return customerNic;
		}
		public void setCustomerNic(String customerNic) {
			this.customerNic = customerNic;
		}
		
		@Column(name = "customerAccount")
		@NotEmpty(message="會員帳號: 請勿空白")
		public String getCustomerAccount() {
			return customerAccount;
		}
		public void setCustomerAccount(String customerAccount) {
			this.customerAccount = customerAccount;
		}
		
		@Column(name = "customerState")
		@NotNull(message="會員狀態: 請勿空白")
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
