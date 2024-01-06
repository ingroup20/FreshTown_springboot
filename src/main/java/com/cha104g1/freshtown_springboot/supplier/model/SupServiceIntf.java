package com.cha104g1.freshtown_springboot.supplier.model;

import java.util.List;

public interface SupServiceIntf {
	
	SupVO addSup(SupVO supVO);
	SupVO updateSupVO(SupVO supVO);
	SupVO getOneSupplierName(String supplierName);
	SupVO getOneSupplierContact(String supplierContact);
	SupVO getOneSupplierStatus(Integer supplierState);
	List<SupVO> getAll();

}
