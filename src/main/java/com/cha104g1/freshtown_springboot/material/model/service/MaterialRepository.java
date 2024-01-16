package com.cha104g1.freshtown_springboot.material.model.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;

public interface MaterialRepository extends JpaRepository<MaterialVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
