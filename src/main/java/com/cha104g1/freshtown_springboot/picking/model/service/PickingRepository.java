package com.cha104g1.freshtown_springboot.picking.model.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.picking.model.model.PickingVO;

public interface PickingRepository extends JpaRepository<PickingVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
