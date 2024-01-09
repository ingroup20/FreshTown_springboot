package com.cha104g1.freshtown_springboot.mealtype.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mealTypeService")
public class MealTypeService {

	@Autowired
	MealTypeRepository repository;
	
	
	public MealTypeService(MealTypeRepository repository) {
		this.repository=repository;
	}
	
	
	public MealTypeVO addMealType(MealTypeVO mealTypeVO) {
		repository.save(mealTypeVO);
		return mealTypeVO;
	}

	
	public MealTypeVO updateMealType(MealTypeVO mealTypeVO) {
		repository.save(mealTypeVO);
		return mealTypeVO;
	}

	
	public MealTypeVO getOneMealType(Integer mealTypeNo) {
		Optional<MealTypeVO> optional = repository.findById(mealTypeNo);
		return optional.orElse(null);
	}
	

	public List<MealTypeVO> getAll() {
		return repository.findAll();
	}




	
}
