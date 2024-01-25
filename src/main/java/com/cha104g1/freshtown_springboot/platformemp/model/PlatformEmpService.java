package com.cha104g1.freshtown_springboot.platformemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreRepository;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.suporder.model.HibernateUtil_CompositeQuery_SupOrder;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;

@Service("platformEmpService")
public class PlatformEmpService implements PlatformEmpServiceIntf{
	
	@Autowired
	PlatformEmpRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public PlatformEmpService(PlatformEmpRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public PlatformEmpVO addPlatformEmp(PlatformEmpVO platformEmpVO) {
		repository.save(platformEmpVO);
		return platformEmpVO;
	}

	@Override
	public PlatformEmpVO updatePlatformEmpVO(PlatformEmpVO platformEmpVO) {
	    repository.save(platformEmpVO);
	    return platformEmpVO;
	}
   
    
	@Override
	public List<PlatformEmpVO> getAllPlatformEmpVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	@Override
	public List<PlatformEmpVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PlatformEmpVO> getPlatformEmpVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PlatformEmpVO getOnePlatformEmp(Integer pEmpId) {
		Optional<PlatformEmpVO> optional = repository.findById(pEmpId);
		return optional.orElse(null); 
	}
	
	public List<SupOrderVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_SupOrder.getAllC(map,sessionFactory.openSession());
	}
	
	
	
	//取得SQL身分帳密(中群)
	public PlatformEmpVO getByPEmpAccount( String pEmpAccount) {
		PlatformEmpVO  platformEmpLogin =repository.findByPEmpAccount(pEmpAccount);
		return platformEmpLogin; 
	}
	
}
