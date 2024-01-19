package com.cha104g1.freshtown_springboot.meals.model;

import java.util.List;
import java.util.Map;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

public interface MealsServiceIntf {

	MealsVO addMealsVO(MealsVO mealsVO);
	MealsVO updateMealsVO(MealsVO mealsVO);
	
	MealsVO getMealsVOByMealNo(Integer mealNo);
	MealsVO getMealsVOByMealName(String mealName);
	MealsVO getMealsVOByMealOnsale(Integer mealOnsale);
//	MealsVO getMealsVOByMealTypeNo(MealTypeVO mealTypeVO);
//	MealsVO getMealsVOByStoresId(StoresVO storesVO);

	List<MealsVO> getAllMealsVO(int currentPage);
	List<MealsVO> getAll();
	
	int getPageTotal();
	
	List<MealsVO> getMealsVOByCompositeQuery(Map<String, String[]> map);
	//店家菜單
	List<MealsVO> getAllByStoreId(Integer storeId);
}
