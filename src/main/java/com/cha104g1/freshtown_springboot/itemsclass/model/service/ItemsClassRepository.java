package com.cha104g1.freshtown_springboot.itemsclass.model.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;

public interface ItemsClassRepository extends JpaRepository<ItemsClassVO, Integer>{
//	@Transactional
//	@Modifying
//	@Query(value = "Select from material where storesId =?1", nativeQuery = true)
//	List<MaterialVO> findByStoresId(int storesId);
}
