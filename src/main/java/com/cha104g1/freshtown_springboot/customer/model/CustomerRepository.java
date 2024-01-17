package com.cha104g1.freshtown_springboot.customer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface CustomerRepository extends JpaRepository<CustomerVO, Integer>{
	
	//登入身分驗證用
	@Transactional
	@Query(value = "from CustomerVO where customerAddress =?1")
	CustomerVO findByCustomerAddress( String customerAddress);
}
