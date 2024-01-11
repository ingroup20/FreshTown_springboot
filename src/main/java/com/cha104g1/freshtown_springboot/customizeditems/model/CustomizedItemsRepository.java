package com.cha104g1.freshtown_springboot.customizeditems.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomizedItemsRepository extends JpaRepository<CustomizedItemsVO, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = " from customized_items where custedName =?1", nativeQuery = true)
	Optional<CustomizedItemsVO> findByCustedName(String custedName);//自訂除了Pk外的查詢方法

}
