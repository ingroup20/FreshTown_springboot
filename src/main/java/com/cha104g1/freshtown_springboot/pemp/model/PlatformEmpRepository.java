package com.cha104g1.freshtown_springboot.pemp.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.login.model.IdentityVO;

public interface PlatformEmpRepository extends JpaRepository<PlatformEmpVO, Integer>{

	@Transactional
	@Query(value = "select pEmpId,pEmpName,pEmpPw,pEmpPerm from p_emp where pEmpAccount =?1", nativeQuery = true)
	PlatformEmpVO findByPEmpAccount( String pEmpAccount);

}
