package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("likeStoreService")
public class LikeStoreService implements LikeStoreServiceIntf{

	@Autowired
	LikeStoreRepository repository ;
	
	public LikeStoreService(LikeStoreRepository repository) {
		this.repository = repository;
	}
	
	
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
	public List<LikeStoreVO> getAllLikeStoreVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Optional<LikeStoreVO> getCustomerAll(int customerId) {
		// 試試看
		return repository.findById(customerId);
	}
	
	@Override
	public List<LikeStoreVO> getAll(int customerId) {
		// 試試看
		return repository.findByCustomerId(customerId);
	}

	@Override
	public List<LikeStoreVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LikeStoreVO> getLikeStoreVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
