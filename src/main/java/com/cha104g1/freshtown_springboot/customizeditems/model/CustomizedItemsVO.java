package com.cha104g1.freshtown_springboot.customizeditems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customized_items")
public class CustomizedItemsVO {
	@OneToMany(mappedBy = "custedItemsNo")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "custedItemsNo", updatable = false)
	private Integer custedItemsNo;
	
	@Column(name = "custedName")
	private String custedName;
	
	public CustomizedItemsVO() {
		super();
	}

	public Integer getCustedItemsNo() {
		return custedItemsNo;
	}

	public void setCustedItemsNo(Integer custedItemsNo) {
		this.custedItemsNo = custedItemsNo;
	}

	public String getCustedName() {
		return custedName;
	}

	public void setCustedName(String custedName) {
		this.custedName = custedName;
	}

	
}
