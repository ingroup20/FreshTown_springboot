package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LikeStoreRepository  extends JpaRepository<LikeStoreVO, Integer> {

	@Transactional
	@Modifying
	@Query(value = "Select from like_store where customerId =?1", nativeQuery = true)
	List<LikeStoreVO> findByCustomerId(int customerId);
	
}