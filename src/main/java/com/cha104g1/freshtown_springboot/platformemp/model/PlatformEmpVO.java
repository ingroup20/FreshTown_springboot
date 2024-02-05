package com.cha104g1.freshtown_springboot.platformemp.model;

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

import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;

@Entity
@Table(name = "p_emp")
public class PlatformEmpVO implements java.io.Serializable {
	
	
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
	@NotEmpty(message="員工密碼: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,18}$", message = "密碼為3~18位大小寫英數字")
	public String getpEmpPw() {
		return pEmpPw;
	}
	public void setpEmpPw(String pEmpPw) {
		this.pEmpPw = pEmpPw;
	}
	
	@Column(name = "pEmpName")
	@NotEmpty(message="員工姓名: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9_\u4E00-\u9FFF_]+$", message = "暱稱只能包含繁體中文或英文")
	public String getpEmpName() {
		return pEmpName;
	}
	public void setpEmpName(String pEmpName) {
		this.pEmpName = pEmpName;
	}
	
	@Column(name = "pEmpEmail")
	@NotEmpty(message="員工信箱: 請勿空白")
	@Size(max = 100)
    @Email
	public String getpEmpEmail() {
		return pEmpEmail;
	}
	public void setpEmpEmail(String pEmpEmail) {
		this.pEmpEmail = pEmpEmail;
	}
	
	@Column(name = "pEmpAccount")
	@NotEmpty(message="員工帳號: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9]{3,18}$", message = "帳號為6~18位大小寫英數字")
	public String getpEmpAccount() {
		return pEmpAccount;
	}
	public void setpEmpAccount(String pEmpAccount) {
		this.pEmpAccount = pEmpAccount;
	}
	
	@Column(name = "pEmpPerm")
	@NotNull(message="員工權限: 請勿空白")
	public Byte getpEmpPerm() {
		return pEmpPerm;
	}
	public void setpEmpPerm(Byte pEmpPerm) {
		this.pEmpPerm = pEmpPerm;
	}
	
	@Column(name = "pEmpState")
	@NotNull(message="員工狀態: 請勿空白")
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
