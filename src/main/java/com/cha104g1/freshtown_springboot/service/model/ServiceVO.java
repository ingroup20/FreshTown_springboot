package com.cha104g1.freshtown_springboot.service.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="service")
public class ServiceVO implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="custSerNo", updatable= false)
	private Integer custSerNo;
    
    @Column(name="pEmpId")
    private Integer pEmpId;
    
    @Column(name="storeId", nullable=true)
    private Integer storeId;
    
    @Column(name="customerId", nullable=true)
    private Integer customerId;
    
    @Column(name="custMessage", length=255)
    private String custMessage;
    
    @Column(name="custTime")
    private Date custTime;
    
	public ServiceVO() {
		super();
	}

	public Integer getCustSerNo() {
		return custSerNo;
	}

	public void setCustSerNo(Integer custSerNo) {
		this.custSerNo = custSerNo;
	}

	public Integer getPEmpId() {
		return pEmpId;
	}

	public void setPEmpId(Integer pEmpId) {
		this.pEmpId = pEmpId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustMessage() {
		return custMessage;
	}

	public void setCustMessage(String custMessage) {
		this.custMessage = custMessage;
	}

	public Date getCustTime() {
		return custTime;
	}

	public void setCustTime(Date custTime) {
		this.custTime = custTime;
	}
	
    
    
}


