package com.cha104g1.freshtown_springboot.customizedorder.model;

import java.util.List;
import java.util.Map;

public interface CustomizedOrderServiceIntf {
	
	CustomizedOrderVO addCustomizedOrderVO(CustomizedOrderVO customizedOrderVO);
	
	CustomizedOrderVO updateCustomizedOrderVO(CustomizedOrderVO customizedOrderVO);
	
	CustomizedOrderVO getCustomizedOrderVOByCustedOrderNo(Integer custedOrderNo);
	
	List<CustomizedOrderVO> getAllCustomizedOrderVOs(int currentPage);
	
	int getPageTotal();
	
	List<CustomizedOrderVO> getCustomizedOrderVOsByCompositeQuery(Map<String, String[]> map);

}
