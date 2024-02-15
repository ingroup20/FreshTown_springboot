package com.cha104g1.freshtown_springboot.storeemp.model;

import java.util.List;
import java.util.Map;

public interface StoreEmpServiceIntf {
	
	StoreEmpVO addStoreEmp(StoreEmpVO storeEmpVO);

	StoreEmpVO updateStoreEmpVO(StoreEmpVO storeEmpVO);

	StoreEmpVO getStoreEmpVOById(Integer sEmpId);
	
	StoreEmpVO getOneStoreEmp(Integer sEmpId);

	List<StoreEmpVO> getAllStoreEmpVO(int currentPage);

	List<StoreEmpVO> getAll();

	int getPageTotal();

	List<StoreEmpVO> getStoreEmpVOsByCompositeQuery(Map<String, String[]> map);

	List<StoreEmpVO> getAll(Map<String, String[]> map);

}
