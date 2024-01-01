package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StoresServiceIntf {
	
	StoresVO addStoresVO(StoresVO storesVO);
	
	StoresVO updateStoresVO(StoresVO storesVO);
	
	StoresVO getStoresVOByStoreId(Integer storeId);
	StoresVO getStoresVOByStoreAccount(String storeAccount);
	StoresVO getStoresVOByStoreEmail(String storeEmail);
	StoresVO getStoresVOByStoreGPS(BigDecimal storeLat, BigDecimal storeLag);
	
	List<StoresVO> getAllStoresVO(int currentPage);
	
	int getPageTotal();
	
	List<StoresVO> getStoresVOByCompositeQuery(Map<String, String[]> map);

}
