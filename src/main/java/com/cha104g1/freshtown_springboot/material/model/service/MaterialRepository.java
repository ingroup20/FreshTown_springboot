package com.cha104g1.freshtown_springboot.material.model.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

public interface MaterialRepository extends JpaRepository<MaterialVO, Integer>{

	MaterialVO findByItemName(String itemName);

	
	//店家頁面查菜單(中群)
	@Transactional
	@Query(value = " from MaterialVO where storeId =?1")
	List<MaterialVO> findAllByStoreId(Integer storeId);
	
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
