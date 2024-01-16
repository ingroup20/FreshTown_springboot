package com.cha104g1.freshtown_springboot.platformemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreRepository;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;

@Service("platformEmpService")
public class PlatformEmpService implements PlatformEmpServiceIntf{
	
	@Autowired
	PlatformEmpRepository repository;
	
	public PlatformEmpService(PlatformEmpRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public PlatformEmpVO addPlatformEmpVO(PlatformEmpVO platformEmpVO) {
		repository.save(platformEmpVO);
		return platformEmpVO;
	}

	@Override
	public PlatformEmpVO updatePlatformEmpVO(PlatformEmpVO platformEmpVO) {
	    repository.save(platformEmpVO);
	    return platformEmpVO;
	}
    
	@Override
	public PlatformEmpVO getPlatformEmpVOById(Integer id) {
		Optional<PlatformEmpVO> optional = repository.findById(id);
		return optional.orElse(null);
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
	
	public PlatformEmpVO getOnePlatformEmp(Integer id) {
		Optional<PlatformEmpVO> optional = repository.findById(id);
		return optional.orElse(null); 
	}
	

}
