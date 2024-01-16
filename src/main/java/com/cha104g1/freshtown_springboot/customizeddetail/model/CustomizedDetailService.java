package com.cha104g1.freshtown_springboot.customizeddetail.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;

@Service("customizedDetailService")
public class CustomizedDetailService implements CustomizedDetailServiceIntf{

	@Autowired
	CustomizedDetailRepository repository;	

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public CustomizedDetailVO addCustomizedDetailVO(CustomizedDetailVO customizedDetailVO) {
		repository.save(customizedDetailVO);
		return customizedDetailVO;
	}

	@Override
	public CustomizedDetailVO updateCustomizedDetailVO(CustomizedDetailVO customizedDetailVO) {
		repository.save(customizedDetailVO);
		return customizedDetailVO;
	}

	@Override
	public CustomizedDetailVO getCustomizedDetailVOByCustedDtlNo(Integer custedDtlNo) {
		Optional<CustomizedDetailVO> optional = repository.findById(custedDtlNo);
		return optional.orElse(null);
	}

//	@Override
//	public CustomizedDetailVO getCustomizedDetailVOByCustedItemsNo(CustomizedItemsVO customizedItemsVO) {
//		Optional<CustomizedDetailVO> optional = repository.findByCustedItemsNo(customizedItemsVO);
//		return optional.orElse(null);
//	}
//
//	@Override
//	public CustomizedDetailVO getCustomizedDetailVOByCustedDtlName(String custedDtlName) {
//		Optional<CustomizedDetailVO> optional = repository.findByCustedDtlName(custedDtlName);
//		return optional.orElse(null);
//	}

	@Override
	public List<CustomizedDetailVO> getAllCustomizedDetailVO(int currentPage) {
		return repository.findAll();
	}

	@Override
	public List<CustomizedDetailVO> getAll() {
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomizedDetailVO> getCustomizedDetailVOByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
