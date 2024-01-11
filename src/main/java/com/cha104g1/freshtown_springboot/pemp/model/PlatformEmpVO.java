package com.cha104g1.freshtown_springboot.pemp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;

@Entity
@Table(name = "p_emp")
public class PlatformEmpVO {
	
	@Id
	private Integer pEmpId;
    private String pEmpPw;
    private String pEmpName;
    private String pEmpEmail;
    private String pEmpAccount;
    private Byte pEmpPerm;
    private Byte pEmpState;
    
    
	public Integer getpEmpId() {
		return pEmpId;
	}
	public void setpEmpId(Integer pEmpId) {
		this.pEmpId = pEmpId;
	}
	public String getpEmpPw() {
		return pEmpPw;
	}
	public void setpEmpPw(String pEmpPw) {
		this.pEmpPw = pEmpPw;
	}
	public String getpEmpName() {
		return pEmpName;
	}
	public void setpEmpName(String pEmpName) {
		this.pEmpName = pEmpName;
	}
	public String getpEmpEmail() {
		return pEmpEmail;
	}
	public void setpEmpEmail(String pEmpEmail) {
		this.pEmpEmail = pEmpEmail;
	}
	public String getpEmpAccount() {
		return pEmpAccount;
	}
	public void setpEmpAccount(String pEmpAccount) {
		this.pEmpAccount = pEmpAccount;
	}
	public Byte getpEmpPerm() {
		return pEmpPerm;
	}
	public void setpEmpPerm(Byte pEmpPerm) {
		this.pEmpPerm = pEmpPerm;
	}
	public Byte getpEmpState() {
		return pEmpState;
	}
	public void setpEmpState(Byte pEmpState) {
		this.pEmpState = pEmpState;
	}
    
    //
	
	@OneToMany(mappedBy="custSerNo", cascade= CascadeType.ALL)
	private Set<ServiceVO> serviceVO;

	public Set<ServiceVO> getServiceVO() {
		return serviceVO;
	}
	public void setServiceVO(Set<ServiceVO> serviceVO) {
		this.serviceVO = serviceVO;
	}
    

}
