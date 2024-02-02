package com.cha104g1.freshtown_springboot.storeemp.model;


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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="store_emp")
public class StoreEmpVO {
	
	    private Integer sEmpId;
	    private String sEmpPw;
	    private String sEmpName;
	    private Byte invPerm;
	    private Byte purPerm;
	    private Byte manuPerm;
	    private Byte orderPerm;
	    private Byte modifyPerm;
	
	    private StoresVO storeId;

	    private String sEmpDeptno;
	    private String sEmpTitle;
	    private Byte sEmpState;
	    
	    @Id
	    @Column(name = "sEmpId")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Integer getsEmpId() {
			return sEmpId;
		}
		public void setsEmpId(Integer sEmpId) {
			this.sEmpId = sEmpId;
		}
		
		@Column(name = "sEmpPw")
		@NotEmpty(message="密碼: 請勿空白")
		@Pattern(regexp = "^[a-zA-Z0-9_]{3,18}$", message = "密碼為3~18位大小寫英數字")
		public String getsEmpPw() {
			return sEmpPw;
		}
		public void setsEmpPw(String sEmpPw) {
			this.sEmpPw = sEmpPw;
		}
		
		@Column(name = "sEmpName")
		@NotEmpty(message="員工姓名: 請勿空白")
		public String getsEmpName() {
			return sEmpName;
		}
		public void setsEmpName(String sEmpName) {
			this.sEmpName = sEmpName;
		}
		
		@Column(name = "invPerm")
		@NotNull(message="庫存權限: 請勿空白")
		public Byte getInvPerm() {
			return invPerm;
		}
		public void setInvPerm(Byte invPerm) {
			this.invPerm = invPerm;
		}
		
		@Column(name = "purPerm")
		@NotNull(message="採購權限: 請勿空白")
		public Byte getPurPerm() {
			return purPerm;
		}
		public void setPurPerm(Byte purPerm) {
			this.purPerm = purPerm;
		}
		
		@Column(name = "manuPerm")
		@NotNull(message="餐點權限: 請勿空白")
		public Byte getManuPerm() {
			return manuPerm;
		}
		public void setManuPerm(Byte manuPerm) {
			this.manuPerm = manuPerm;
		}
		
		@Column(name = "orderPerm")
		@NotNull(message="訂單權限: 請勿空白")
		public Byte getOrderPerm() {
			return orderPerm;
		}
		public void setOrderPerm(Byte orderPerm) {
			this.orderPerm = orderPerm;
		}
		
		@Column(name = "modifyPerm")
		@NotNull(message="修改權限: 請勿空白")
		public Byte getModifyPerm() {
			return modifyPerm;
		}
		public void setModifyPerm(Byte modifyPerm) {
			this.modifyPerm = modifyPerm;
		}

		@Column(name = "sEmpDeptno")
		@NotEmpty(message="部門編號: 請勿空白")
		public String getsEmpDeptno() {
			return sEmpDeptno;
		}
		public void setsEmpDeptno(String sEmpDeptno) {
			this.sEmpDeptno = sEmpDeptno;
		}
		
		@Column(name = "sEmpTitle")
		@NotEmpty(message="員工職稱: 請勿空白")
		public String getsEmpTitle() {
			return sEmpTitle;
		}
		public void setsEmpTitle(String sEmpTitle) {
			this.sEmpTitle = sEmpTitle;
		}
		
		@Column(name = "sEmpState")
		@NotNull(message="員工狀態: 請勿空白")
		public Byte getsEmpState() {
			return sEmpState;
		}
		public void setsEmpState(Byte sEmpState) {
			this.sEmpState = sEmpState;
		}

		@ManyToOne
		@JoinColumn(name="storeId",referencedColumnName ="storeId")
		public StoresVO getStoresVO() {
			return storeId;
		}
		//
		public void setStoresVO(StoresVO storeId) {
			this.storeId = storeId;
		}
	    
		//領料表
		private Set<PickingVO> pickingVO;
		
		@OneToMany(mappedBy = "storeEmpVO" ,cascade= CascadeType.ALL)
		public Set<PickingVO> getPickingVO(){
			return pickingVO;
		}
		
		public void setPickingVO(Set<PickingVO> pickingVO) {
			this.pickingVO = pickingVO;
		}
		
	    
	    

}
