package com.cha104g1.freshtown_springboot.customizeddetail.model;

import java.util.List;
import java.util.Map;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;

public interface CustomizedDetailServiceIntf {

	
	CustomizedDetailVO addCustomizedDetailVO(CustomizedDetailVO customizedDetailVO);
	CustomizedDetailVO updateCustomizedDetailVO(CustomizedDetailVO customizedDetailVO);
	
	CustomizedDetailVO getCustomizedDetailVOByCustedDtlNo(Integer custedDtlNo);
//	CustomizedDetailVO getCustomizedDetailVOByCustedItemsNo(CustomizedItemsVO customizedItemsVO);
//	CustomizedDetailVO getCustomizedDetailVOByCustedDtlName(String custedDtlName);

	List<CustomizedDetailVO> getAllCustomizedDetailVO(int currentPage);
	List<CustomizedDetailVO> getAll();
	
	int getPageTotal();
	
	List<CustomizedDetailVO> getCustomizedDetailVOByCompositeQuery(Map<String, String[]> map);
}
