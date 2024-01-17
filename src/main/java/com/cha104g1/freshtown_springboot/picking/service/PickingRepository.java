package com.cha104g1.freshtown_springboot.picking.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.picking.model.PickingVO;

public interface PickingRepository extends JpaRepository<PickingVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
