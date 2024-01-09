package com.cha104g1.freshtown_springboot.mealtype.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealTypeRepository  extends JpaRepository<MealTypeVO, Integer> {
	
}