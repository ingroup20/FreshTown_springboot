package com.cha104g1.freshtown_springboot.customizeddetail.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;

public interface CustomizedDetailRepository extends JpaRepository<CustomizedDetailVO, Integer>{

	//中群
	@Transactional
	@Query(value = "FROM CustomizedDetailVO c WHERE c.customizedItemsVO.custedItemsNo = ?1")
	List<CustomizedDetailVO> findByCustedItemsNo(Integer custedItemsNo);
//
//	@Transactional
//	@Modifying
//	@Query(value = " from customized_detail where custedDtlName =?1", nativeQuery = true)
//	Optional<CustomizedDetailVO> findByCustedDtlName(String custedDtlName);//自訂除了Pk外的查詢方法

}
