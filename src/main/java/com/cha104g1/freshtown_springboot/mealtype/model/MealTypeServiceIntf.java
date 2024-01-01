package com.cha104g1.freshtown_springboot.mealtype.model;

import java.util.List;
import java.util.Map;

public interface MealTypeServiceIntf {
	
	MealTypeVO addMealTypeVO(MealTypeVO mealTypeVO);
	
	MealTypeVO updateMealTypeVO(MealTypeVO mealTypeVO);
	
	MealTypeVO getMealTypeVOByMealTypeNo(Integer mealTypeNo);
	
	List<MealTypeVO> getAllMealTypeVOs(int currentPage);
	
	int getPageTotal();
	
	List<MealTypeVO> getMealTypeVOsByCompositeQuery(Map<String, String[]> map);

}
