package com.cha104g1.freshtown_springboot.orders.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersVO, Integer> {
    
		//登入身分篩選
		@Transactional
		@Query(value = "from OrdersVO where storeId =?1")
		List<OrdersVO> findAllByStoreId( Integer storeId);
}


