package com.cha104g1.freshtown_springboot.mealtype.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mealTypeService")
public class MealTypeService implements MealTypeServiceIntf{

	@Autowired
	MealTypeRepository repository;
	
	
	public MealTypeService(MealTypeRepository repository) {
		this.repository=repository;
	}
	
	@Override
	public MealTypeVO addMealTypeVO(MealTypeVO mealTypeVO) {
		repository.save(mealTypeVO);
		return mealTypeVO;
	}

	@Override
	public MealTypeVO updateMealTypeVO(MealTypeVO mealTypeVO) {
		repository.save(mealTypeVO);
		return mealTypeVO;
	}

	@Override
	public MealTypeVO getMealTypeVOByMealTypeNo(Integer mealTypeNo) {
		
		return null;
	}

	@Override
	public List<MealTypeVO> getAllMealTypeVOs(int currentPage) {

		return null;
	}

	@Override
	public List<MealTypeVO> getAll() {

		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MealTypeVO> getMealTypeVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
