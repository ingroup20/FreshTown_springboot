package com.cha104g1.freshtown_springboot.storeemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;



@Service("StoreEmpService")
public class StoreEmpService implements StoreEmpServiceIntf{
	
	@Autowired
	StoreEmpRepository repository;
	
	public StoreEmpService(StoreEmpRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public StoreEmpVO addStoreEmpVO(StoreEmpVO storeEmpVO) {
		repository.save(storeEmpVO);
		return storeEmpVO;
	}

	@Override
	public StoreEmpVO updateStoreEmpVO(StoreEmpVO storeEmpVO) {
	    repository.save(storeEmpVO);
	    return storeEmpVO;
	}
    
	@Override
	public StoreEmpVO getStoreEmpVOById(Integer id) {
		Optional<StoreEmpVO> optional = repository.findById(id);
		return optional.orElse(null);
	}
    
	@Override
	public List<StoreEmpVO> getAllStoreEmpVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	@Override
	public List<StoreEmpVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StoreEmpVO> getStoreEmpVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public StoreEmpVO getOneStoreEmp(Integer id) {
		Optional<StoreEmpVO> optional = repository.findById(id);
		return optional.orElse(null); 
	}

}
