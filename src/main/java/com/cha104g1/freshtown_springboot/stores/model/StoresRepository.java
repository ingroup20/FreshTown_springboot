package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StoresRepository extends JpaRepository<StoresVO, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = " from stores where storeAccount =?1", nativeQuery = true)
	Optional<StoresVO> findByStoreAccount(String storeAccount);//自訂除了Pk外的查詢方法
	
	@Transactional
	@Modifying
	@Query(value = " from stores where storeEmail =?1", nativeQuery = true)
	Optional<StoresVO> findByStoreEmail(String storeEmail);//自訂除了Pk外的查詢方法
	
	@Transactional
	@Modifying
	@Query(value = " from stores where storeLat =?1 and storeLag =?2", nativeQuery = true)
	Optional<StoresVO> findByStoreGPS(BigDecimal storeLat, BigDecimal storeLag);//自訂除了Pk外的查詢方法

}