package com.cha104g1.freshtown_springboot.customer.model;

import java.util.List;
import java.util.Map;

public interface CustomerServiceIntf {
	
	CustomerVO addCustomerVO(CustomerVO customerVO);

	CustomerVO updateCustomerVO(CustomerVO customerVO);

	CustomerVO getByCustomerId(Integer customerId);

	List<CustomerVO> getAllCustomerVO(int currentPage);

	List<CustomerVO> getAll();

	int getPageTotal();

	List<CustomerVO> getCustomerVOsByCompositeQuery(Map<String, String[]> map);

}
