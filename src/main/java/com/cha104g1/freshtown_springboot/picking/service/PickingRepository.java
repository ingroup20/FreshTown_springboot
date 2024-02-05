package com.cha104g1.freshtown_springboot.picking.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.picking.model.PickingVO;

public interface PickingRepository extends JpaRepository<PickingVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
	
	@Transactional
	@Query(value = " from PickingVO where storeId =?1")
	List<PickingVO> findAllByStoreId(Integer storeId);
}
