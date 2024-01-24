package com.cha104g1.freshtown_springboot.meals.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Service("mealsService")
public class MealsService implements MealsServiceIntf{

	@Autowired
	MealsRepository repository;	

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public MealsVO addMealsVO(MealsVO mealsVO) {
		repository.save(mealsVO);
		return mealsVO;
	}

	@Override
	public MealsVO updateMealsVO(MealsVO mealsVO) {
		repository.save(mealsVO);
		return mealsVO;
	}

	@Override
	public MealsVO getMealsVOByMealNo(Integer mealNo) {
		Optional<MealsVO> optional = repository.findById(mealNo);
		return optional.orElse(null);
	}

	@Override
	public MealsVO getMealsVOByMealName(String mealName) {
		Optional<MealsVO> optional = repository.findByMealName(mealName);
		return optional.orElse(null);
	}

	@Override
	public MealsVO getMealsVOByMealOnsale(Integer mealOnsale) {
		Optional<MealsVO> optional = repository.findByMealOnsale(mealOnsale);
		return optional.orElse(null);
	}

//	@Override
//	public MealsVO getMealsVOByMealTypeNo(MealTypeVO mealTypeVO) {
//		Optional<MealsVO> optional = repository.findByMealTypeNo(mealTypeVO);
//		return optional.orElse(null);
//	}
//
//	@Override
//	public MealsVO getMealsVOByStoresId(StoresVO storesVO) {
//		Optional<MealsVO> optional = repository.findByStoresId(storesVO);
//		return optional.orElse(null);
//	}

	@Override
	public List<MealsVO> getAllMealsVO(int currentPage) {
		return repository.findAll();
	}

	@Override
	public List<MealsVO> getAll() {
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MealsVO> getMealsVOByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MealsVO> getAllByStoreId(Integer storeId) {
		List<MealsVO> list = repository.findAllByStoreId(storeId);
		return list;
	}

	
	
}
