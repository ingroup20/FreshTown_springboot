package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeStoreService")
public class LikeStoreService {

	@Autowired
	LikeStoreRepository repository ;
	    
	public LikeStoreService(LikeStoreRepository repository) {
		this.repository = repository;
	}
	
	

	public LikeStoreVO addLikeStoreVO(LikeStoreVO likeStoreVO) {
		repository.save(likeStoreVO);
		return likeStoreVO;
	}

	
	public LikeStoreVO updateLikeStoreVO(LikeStoreVO likeStoreVO) {
		repository.save(likeStoreVO);
		return likeStoreVO;
	}

	
	public LikeStoreVO getLikeStoreVOById(Integer id) {
		Optional<LikeStoreVO> optional = repository.findById(id);
		return optional.orElse(null);
	}


	public List<LikeStoreVO> getAllLikeStoreVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
	public List<LikeStoreVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
//	public List<LikeStoreVO> getAll(int customerId) {
//		return repository.findByCustomerId(customerId);
//	}
	
	

}
