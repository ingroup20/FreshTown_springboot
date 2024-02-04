package com.cha104g1.freshtown_springboot.customizedorder.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

public interface CustomizedOrderRepository extends JpaRepository<CustomizedOrderVO, Integer> {

			@Transactional
			@Query(value = "FROM CustomizedOrderVO c WHERE c.orderDetailVO = ?1")
			List<CustomizedOrderVO> findByOrderDetailVO(OrderDetailVO orderDetailVO);
			
	
	
}