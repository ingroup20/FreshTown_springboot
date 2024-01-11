package com.cha104g1.freshtown_springboot.customizeditems.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;

@Service("customizedItemsService")
public class CustomizedItemsService implements CustomizedItemsServiceIntf{

	@Autowired
	CustomizedItemsRepository repository;	

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public CustomizedItemsVO addCustomizedItemsVO(CustomizedItemsVO customizedItemsVO) {
		repository.save(customizedItemsVO);
		return customizedItemsVO;
	}

	@Override
	public CustomizedItemsVO updateCustomizedItemsVO(CustomizedItemsVO customizedItemsVO) {
		repository.save(customizedItemsVO);
		return customizedItemsVO;
	}

	@Override
	public CustomizedItemsVO getCustomizedItemsVOByCustedItemsNo(Integer custedItemsNo) {
		Optional<CustomizedItemsVO> optional = repository.findById(custedItemsNo);
		return optional.orElse(null);
	}

	@Override
	public CustomizedItemsVO getCustomizedItemsVOByCustedName(String custedName) {
		Optional<CustomizedItemsVO> optional = repository.findByCustedName(custedName);
		return optional.orElse(null);
	}

	@Override
	public List<CustomizedItemsVO> getAllCustomizedItemsVO(int currentPage) {
		return repository.findAll();
	}

	@Override
	public List<CustomizedItemsVO> getAll() {
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomizedItemsVO> getCustomizedItemsVOByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
