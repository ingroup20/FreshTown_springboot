package com.cha104g1.freshtown_springboot.storeemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.platformemp.model.HibernateUtil_CompositeQuery_PlatformEmp;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;



@Service("StoreEmpService")
public class StoreEmpService implements StoreEmpServiceIntf{
	
	@Autowired
	StoreEmpRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public StoreEmpService(StoreEmpRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public StoreEmpVO addStoreEmp(StoreEmpVO storeEmpVO) {
		repository.save(storeEmpVO);
		return storeEmpVO;
	}

	@Override
	public StoreEmpVO updateStoreEmpVO(StoreEmpVO storeEmpVO) {
	    repository.save(storeEmpVO);
	    return storeEmpVO;
	}
    
	@Override
	public StoreEmpVO getStoreEmpVOById(Integer sEmpId) {
		Optional<StoreEmpVO> optional = repository.findById(sEmpId);
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
	
	@Override
	public StoreEmpVO getOneStoreEmp(Integer sEmpId) {
		Optional<StoreEmpVO> optional = repository.findById(sEmpId);
		return optional.orElse(null); 
	}
	
	@Override
	public List<StoreEmpVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_StoreEmp.getAllC(map, sessionFactory.openSession());
	}

	//取得SQL身分帳密(中群)
	public StoreEmpVO getBySEmpId( Integer sEmpId) {
		StoreEmpVO  storeEmpLogin =repository.findBySEmpId(sEmpId);
		return storeEmpLogin; 
	}
}
