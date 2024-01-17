package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LikeStoreRepository  extends JpaRepository<LikeStoreVO, Integer> {

//	@Transactional
//	@Query(value = "from likeStoreVO where customerId =?1")
//	List<LikeStoreVO> findByCustomerId(int customerId);
	
}