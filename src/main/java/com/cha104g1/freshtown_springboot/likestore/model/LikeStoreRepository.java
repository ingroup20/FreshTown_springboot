package com.cha104g1.freshtown_springboot.likestore.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LikeStoreRepository  extends JpaRepository<LikeStoreVO, Integer> {

	@Transactional
	@Query(value = "from LikeStoreVO  where customerId =?1 and likeUnlike='L' ")
	List<LikeStoreVO> findAllLikeByCustomerId(int customerId);
	
	@Transactional
	@Query(value = "from LikeStoreVO  where customerId =?1 and likeUnlike='U' ")
	List<LikeStoreVO> findAllUnlikeByCustomerId(int customerId);
	
}