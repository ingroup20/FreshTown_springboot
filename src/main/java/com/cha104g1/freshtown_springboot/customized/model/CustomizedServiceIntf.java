package com.cha104g1.freshtown_springboot.customized.model;

import java.util.List;
import java.util.Map;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

public interface CustomizedServiceIntf {

	CustomizedVO addCustomizedVO(CustomizedVO customizedVO);
	
	CustomizedVO updateCustomizedVO(CustomizedVO customizedVO);
	
	CustomizedVO getCustomizedVOByCompositeKey(Integer mealNo, Integer custedItemsNo);
	CustomizedVO getCustomizedVOByCustedStatus(Integer custedStatus);
	
	List<CustomizedVO> getAllCustomizedVO(int currentPage);
	List<CustomizedVO> getAll();
	
	int getPageTotal();
	
	List<CustomizedVO> getCustomizedVOByCompositeQuery(Map<String, String[]> map);
}
