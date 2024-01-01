package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;
import java.util.Map;

public interface LikeStoreServiceIntf {
	
	LikeStoreVO addLikeStoreVO(LikeStoreVO likeStoreVO);
	
	LikeStoreVO updateLikeStoreVO(LikeStoreVO likeStoreVO);
	
	LikeStoreVO getLikeStoreVOById(Integer id);
	
	List<LikeStoreVO> getAllLikeStoreVOs(int currentPage);
	
	int getPageTotal();
	
	List<LikeStoreVO> getLikeStoreVOsByCompositeQuery(Map<String, String[]> map);

}
