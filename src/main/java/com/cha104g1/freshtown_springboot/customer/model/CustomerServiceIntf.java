package com.cha104g1.freshtown_springboot.customer.model;

import java.util.List;
import java.util.Map;

import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

public interface CustomerServiceIntf {
	
	CustomerVO addCustomer(CustomerVO customerVO);

	CustomerVO updateCustomerVO(CustomerVO customerVO);

	CustomerVO getByCustomerId(Integer customerId);
	
	CustomerVO getOneCustomer(Integer customerId);

	List<CustomerVO> getAllCustomerVO(int currentPage);

	List<CustomerVO> getAll();
	
    List<CustomerVO> getAllByStoreId(Integer storeId);
	
	List<CustomerVO> getAll(Map<String, String[]> map);

	int getPageTotal();

	List<CustomerVO> getCustomerVOsByCompositeQuery(Map<String, String[]> map);

}
