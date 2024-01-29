package com.cha104g1.freshtown_springboot.customized.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

@Service("customizedService")
public class CustomizedService implements CustomizedServiceIntf {

	@Autowired
	CustomizedRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CustomizedVO addCustomizedVO(CustomizedVO customizedVO) {
		repository.save(customizedVO);
		return customizedVO;
	}

	@Override
	public CustomizedVO updateCustomizedVO(CustomizedVO customizedVO) {
		repository.save(customizedVO);
		return customizedVO;
	}

	@Override
	public CustomizedVO getCustomizedVOByCompositeKey(Integer mealNo, Integer custedItemsNo) {
		Optional<CustomizedVO> optional = repository.findByCompositeKey(mealNo, custedItemsNo);
		return optional.orElse(null);
	}

	@Override
	public CustomizedVO getCustomizedVOByCustedStatus(Integer custedStatus) {
		Optional<CustomizedVO> optional = repository.findByCustedStatus(custedStatus);
		return optional.orElse(null);
	}

	@Override
	public List<CustomizedVO> getAllCustomizedVO(int currentPage) {
		return repository.findAll();
	}

	@Override
	public List<CustomizedVO> getAll() {
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomizedVO> getCustomizedVOByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	// 中群
	@Override
	public List<CustomizedVO> getAll(Integer mealNo) {
		List<CustomizedVO> list = repository.findByMealNo(mealNo);
		return list;
	}

}
