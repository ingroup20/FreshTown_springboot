package com.cha104g1.freshtown_springboot.material.model.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

public interface MaterialServiceIntf {

	MaterialVO addMaterial(MaterialVO materialVO);
	MaterialVO updateMaterial(MaterialVO materialVO);
	

	MaterialVO findMaterialByItemName(String itemName);
	MaterialVO getOneMaterial(Integer itemNumber);
	List<MaterialVO> getAll();
	List<MaterialVO> getAll(Map<String, String[]> map);
	Set<PickingVO> getpickingVOByPickingNo(Integer pickingNo);
	List<MaterialVO> getAllByStoreId(Integer storeId);
}
