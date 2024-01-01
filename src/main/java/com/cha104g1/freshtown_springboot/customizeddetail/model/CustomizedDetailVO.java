package com.cha104g1.freshtown_springboot.customizeddetail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "customized_detail")
public class CustomizedDetailVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "custedDtlNo", updatable = false)
	private Integer custedDtlNo;
	
	@JoinColumn(name = "custedItemsNo", referencedColumnName = "custedItemsNo")
	private Integer custedItemsNo;
	
	@Column(name = "custedDtlName")
	private String custedDtlName;
	
	public CustomizedDetailVO() {
		super();
	}

	public Integer getCustedDtlNo() {
		return custedDtlNo;
	}

	public void setCustedDtlNo(Integer custedDtlNo) {
		this.custedDtlNo = custedDtlNo;
	}

	public Integer getCustedItemsNo() {
		return custedItemsNo;
	}

	public void setCustedItemsNo(Integer custedItemsNo) {
		this.custedItemsNo = custedItemsNo;
	}

	public String getCustedDtlName() {
		return custedDtlName;
	}

	public void setCustedDtlName(String custedDtlName) {
		this.custedDtlName = custedDtlName;
	}
	
	
}
