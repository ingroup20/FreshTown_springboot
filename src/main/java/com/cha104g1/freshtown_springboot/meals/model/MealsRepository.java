package com.cha104g1.freshtown_springboot.meals.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

public interface MealsRepository extends JpaRepository<MealsVO, Integer>{

	@Transactional
	@Modifying
	@Query(value = " from meals where mealName =?1", nativeQuery = true)
	Optional<MealsVO> findByMealName(String mealName);//自訂除了Pk外的查詢方法

	@Transactional
	@Modifying
	@Query(value = " from meals where mealOnsale =?1", nativeQuery = true)
	Optional<MealsVO> findByMealOnsale(Integer mealOnsale);//自訂除了Pk外的查詢方法

//	@Transactional
//	@Modifying
//	@Query(value = " from meals where mealTypeNo =?1", nativeQuery = true)
//	Optional<MealsVO> findByMealTypeNo(MealTypeVO mealTypeVO);//自訂除了Pk外的查詢方法
//
//	@Transactional
//	@Modifying
//	@Query(value = " from meals where storeId =?1", nativeQuery = true)
//	Optional<MealsVO> findByStoresId(StoresVO storesVO);//自訂除了Pk外的查詢方法

	
}
