package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LikeStoreServiceIntf {
	
	LikeStoreVO addLikeStoreVO(LikeStoreVO likeStoreVO);
	
	LikeStoreVO updateLikeStoreVO(LikeStoreVO likeStoreVO);
	
	LikeStoreVO getLikeStoreVOById(Integer id);
	
	List<LikeStoreVO> getAllLikeStoreVO(int currentPage);
	 Optional<LikeStoreVO> getCustomerAll(int customerId);
	 List<LikeStoreVO> getAll(int customerId);
	 List<LikeStoreVO> getAll();
	
	int getPageTotal();
	
	List<LikeStoreVO> getLikeStoreVOsByCompositeQuery(Map<String, String[]> map);

	

}
