package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeStoreService")
public class LikeStoreService  implements LikeStoreServiceIntf{

	@Autowired
	LikeStoreRepository repository ;
	
	//應該要能自動注入建構子
//	public LikeStoreService(LikeStoreRepository repository) {
//		this.repository = repository;
//	}
	
	//============================================================
	@Override
	public LikeStoreVO addLikeStoreVO(LikeStoreVO likeStoreVO) {
		repository.save(likeStoreVO);
		return likeStoreVO;
	}

	@Override
	public LikeStoreVO updateLikeStoreVO(LikeStoreVO likeStoreVO) {
		repository.save(likeStoreVO);
		return likeStoreVO;
	}

	@Override
	public LikeStoreVO getLikeStoreVOById(Integer id) {
		Optional<LikeStoreVO> optional = repository.findById(id);
		return optional.orElse(null);
	}



	@Override
	public List<LikeStoreVO> getAll() {
		return repository.findAll();
	}

	@Override
	public List<LikeStoreVO> getAllByCustomer(int customerId, String likeUnlike) {
		//預設顯示收藏店家 likeUnlike.equals("L")
		List<LikeStoreVO> list ;
		
		if(likeUnlike.equals("U"))
			list=repository.findAllUnlikeByCustomerId(customerId);
		else 
			list=repository.findAllLikeByCustomerId(customerId);
			
		return list;
	}


	

	
	

}
