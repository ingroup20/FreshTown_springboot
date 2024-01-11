package com.cha104g1.freshtown_springboot.customizeditems.model;

import java.util.List;
import java.util.Map;

public interface CustomizedItemsServiceIntf {

	CustomizedItemsVO addCustomizedItemsVO(CustomizedItemsVO customizedItemsVO);
	CustomizedItemsVO updateCustomizedItemsVO(CustomizedItemsVO customizedItemsVO);
	
	CustomizedItemsVO getCustomizedItemsVOByCustedItemsNo(Integer custedItemsNo);
	CustomizedItemsVO getCustomizedItemsVOByCustedName(String custedName);

	List<CustomizedItemsVO> getAllCustomizedItemsVO(int currentPage);
	List<CustomizedItemsVO> getAll();
	
	int getPageTotal();
	
	List<CustomizedItemsVO> getCustomizedItemsVOByCompositeQuery(Map<String, String[]> map);
	
}
