package com.cha104g1.freshtown_springboot.service.model.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;

public interface ServiceRepository extends JpaRepository<ServiceVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
