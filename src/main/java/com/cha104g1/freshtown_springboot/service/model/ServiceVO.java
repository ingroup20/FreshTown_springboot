package com.cha104g1.freshtown_springboot.service.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
@Entity
@Table(name="service")
public class ServiceVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="custSerNo", updatable= false)
	private Integer custSerNo;
    
    @ManyToOne
    @JoinColumn(name="pEmpId", referencedColumnName="pEmpId")
    private PlatformEmpVO platformEmpVO;
    
    @ManyToOne
    @JoinColumn(name="storeId", referencedColumnName="storeId")
    private StoresVO storesVO;
    
    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName="customerId")
    private CustomerVO customerVO;
    
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

	//
	public PlatformEmpVO getPlatformEmpVO() {
		return platformEmpVO;
	}
	//
	public void setPlatformEmpVO(PlatformEmpVO platformEmpVO) {
		this.platformEmpVO = platformEmpVO;
	}
	//
	public StoresVO getStoresVO() {
		return storesVO;
	}
	//
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	//
	public CustomerVO getCustomerVO() {
		return customerVO;
	}
	//
	public void setCustomerVO(CustomerVO customerVO) {
		this.customerVO = customerVO;
	}
	
	
	
    
    
}


