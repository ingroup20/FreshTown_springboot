package com.cha104g1.freshtown_springboot.pemp.model;

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
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;

@Entity
@Table(name = "p_emp")
public class PlatformEmpVO {
	
	
	private Integer pEmpId;
    private String pEmpPw;
    private String pEmpName;
    private String pEmpEmail;
    private String pEmpAccount;
    private Byte pEmpPerm;
    private Byte pEmpState;
    
    @Id
    @Column(name = "pEmpId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getpEmpId() {
		return pEmpId;
	}
	public void setpEmpId(Integer pEmpId) {
		this.pEmpId = pEmpId;
	}
	
	@Column(name = "pEmpPw")
	public String getpEmpPw() {
		return pEmpPw;
	}
	public void setpEmpPw(String pEmpPw) {
		this.pEmpPw = pEmpPw;
	}
	
	@Column(name = "pEmpName")
	public String getpEmpName() {
		return pEmpName;
	}
	public void setpEmpName(String pEmpName) {
		this.pEmpName = pEmpName;
	}
	
	@Column(name = "pEmpEmail")
	public String getpEmpEmail() {
		return pEmpEmail;
	}
	public void setpEmpEmail(String pEmpEmail) {
		this.pEmpEmail = pEmpEmail;
	}
	
	@Column(name = "pEmpAccount")
	public String getpEmpAccount() {
		return pEmpAccount;
	}
	public void setpEmpAccount(String pEmpAccount) {
		this.pEmpAccount = pEmpAccount;
	}
	
	@Column(name = "pEmpPerm")
	public Byte getpEmpPerm() {
		return pEmpPerm;
	}
	public void setpEmpPerm(Byte pEmpPerm) {
		this.pEmpPerm = pEmpPerm;
	}
	
	@Column(name = "pEmpState")
	public Byte getpEmpState() {
		return pEmpState;
	}
	public void setpEmpState(Byte pEmpState) {
		this.pEmpState = pEmpState;
	}
    
    //
	

	private Set<ServiceVO> serviceVO;

	@OneToMany(mappedBy="custSerNo", cascade= CascadeType.ALL)
	public Set<ServiceVO> getServiceVO() {
		return serviceVO;
	}
	public void setServiceVO(Set<ServiceVO> serviceVO) {
		this.serviceVO = serviceVO;
	}
    

}
