package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LikeStoreServiceIntf {
	
	LikeStoreVO addLikeStoreVO(LikeStoreVO likeStoreVO);	
	
	LikeStoreVO updateLikeStoreVO(LikeStoreVO likeStoreVO);	
	
	LikeStoreVO getLikeStoreVOById(Integer id);
		
	List<LikeStoreVO> getAll();
	List<LikeStoreVO> getAllByCustomer(int customerId,String likeUnlike);
//	List<LikeStoreVO> getAll(Map<String, String[]> map);



	

}
