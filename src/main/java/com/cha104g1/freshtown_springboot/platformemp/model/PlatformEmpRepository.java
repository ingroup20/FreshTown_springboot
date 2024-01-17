package com.cha104g1.freshtown_springboot.platformemp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlatformEmpRepository extends JpaRepository<PlatformEmpVO, Integer>{

	//登入身分驗證用
	@Transactional
	@Query(value = "from PlatformEmpVO where pEmpAccount =?1")
	PlatformEmpVO findByPEmpAccount( String pEmpAccount);
}
