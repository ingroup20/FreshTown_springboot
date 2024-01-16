package com.cha104g1.freshtown_springboot.customized.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

public interface CustomizedRepository extends JpaRepository<CustomizedVO, Integer>{

	@Transactional
	@Modifying
	@Query(value = " from customized where mealNo =?1 and custedItemsNo =?2", nativeQuery = true)
	Optional<CustomizedVO> findByCompositeKey(Integer mealNo, Integer custedItemsNo);//自訂除了Pk外的查詢方法

	@Transactional
	@Modifying
	@Query(value = " from customized where custedStatus =?1", nativeQuery = true)
	Optional<CustomizedVO> findByCustedStatus(Integer custedStatus);//自訂除了Pk外的查詢方法

}
