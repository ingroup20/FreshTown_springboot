package com.cha104g1.freshtown_springboot.storeemp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface StoreEmpRepository extends JpaRepository<StoreEmpVO, Integer>{
	//登入身分驗證用
	@Transactional
	@Query(value = "from StoreEmpVO where sEmpId =?1")
	StoreEmpVO findBySEmpId( String sEmpId);
}
