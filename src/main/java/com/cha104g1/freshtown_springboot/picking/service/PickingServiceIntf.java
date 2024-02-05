package com.cha104g1.freshtown_springboot.picking.service;

import java.util.List;
import java.util.Map;

import com.cha104g1.freshtown_springboot.picking.model.PickingVO;

public interface PickingServiceIntf {

	PickingVO addPicking(PickingVO pickingVO);
	PickingVO updatePicking(PickingVO pickingVO);
	PickingVO getOnePicking(Integer pickingNo);
		
	List<PickingVO> getAllByStoreId(Integer storeId);
	
	List<PickingVO> getAll(Map<String, String[]> map);
	
	List<PickingVO> getAll();
}
