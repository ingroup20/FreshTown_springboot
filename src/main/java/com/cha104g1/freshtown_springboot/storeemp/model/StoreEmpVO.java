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


import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;


@Entity
@Table(name="store_emp")
public class StoreEmpVO {
	
	    private Integer sEmpId;
	    private String sEmpName;
	    private Byte invPerm;
	    private Byte purPerm;
	    private Byte manuPerm;
	    private Byte orderPerm;
	    private Byte modifyPerm;
	
	    private StoresVO storesVO;

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
		
		@Column(name = "sEmpName")
		public String getsEmpName() {
			return sEmpName;
		}
		public void setsEmpName(String sEmpName) {
			this.sEmpName = sEmpName;
		}
		
		@Column(name = "invPerm")
		public Byte getInvPerm() {
			return invPerm;
		}
		public void setInvPerm(Byte invPerm) {
			this.invPerm = invPerm;
		}
		
		@Column(name = "purPerm")
		public Byte getPurPerm() {
			return purPerm;
		}
		public void setPurPerm(Byte purPerm) {
			this.purPerm = purPerm;
		}
		
		@Column(name = "manuPerm")
		public Byte getManuPerm() {
			return manuPerm;
		}
		public void setManuPerm(Byte manuPerm) {
			this.manuPerm = manuPerm;
		}
		
		@Column(name = "orderPerm")
		public Byte getOrderPerm() {
			return orderPerm;
		}
		public void setOrderPerm(Byte orderPerm) {
			this.orderPerm = orderPerm;
		}
		
		@Column(name = "modifyPerm")
		public Byte getModifyPerm() {
			return modifyPerm;
		}
		public void setModifyPerm(Byte modifyPerm) {
			this.modifyPerm = modifyPerm;
		}

		
		public String getsEmpDeptno() {
			return sEmpDeptno;
		}
		public void setsEmpDeptno(String sEmpDeptno) {
			this.sEmpDeptno = sEmpDeptno;
		}
		public String getsEmpTitle() {
			return sEmpTitle;
		}
		public void setsEmpTitle(String sEmpTitle) {
			this.sEmpTitle = sEmpTitle;
		}
		public Byte getsEmpState() {
			return sEmpState;
		}
		public void setsEmpState(Byte sEmpState) {
			this.sEmpState = sEmpState;
		}

		@ManyToOne
		@JoinColumn(name="storeId",referencedColumnName ="storeId")
		public StoresVO getStoresVO() {
			return storesVO;
		}
		//
		public void setStoresVO(StoresVO storesVO) {
			this.storesVO = storesVO;
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
