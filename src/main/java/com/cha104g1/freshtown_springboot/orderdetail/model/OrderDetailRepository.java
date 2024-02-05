package com.cha104g1.freshtown_springboot.orderdetail.model;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface OrderDetailRepository extends JpaRepository<OrderDetailVO, Integer>{
	
	
	@Transactional
	@Modifying
	@Query(value = " from OrderDetailVO c where c.ordersVO.orderId =?1")
	List<OrderDetailVO> findByOrderId(Integer orderId);

}
