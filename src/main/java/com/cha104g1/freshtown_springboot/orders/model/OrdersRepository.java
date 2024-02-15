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
		@Query(value = "from OrdersVO where storeId =?1 order by storeId asc")
		List<OrdersVO> findAllByStoreId( Integer storeId);
		
		//登入身分篩選
		@Transactional
//		@Query(value = "from OrdersVO where storeId =?1 and orderTime >= DATE_SUB(NOW(), INTERVAL 1 DAY) order by storeId asc ")
		@Query(value = "SELECT * FROM orders WHERE storeId = ?1 AND orderTime >= CURDATE() - INTERVAL 1 DAY ORDER BY orderId ", nativeQuery = true)
		List<OrdersVO> findAllTodayByStoreId( Integer storeId);
		
		//登入身分篩選
		@Transactional
		@Query(value = "from OrdersVO where CustomerId =?1 order by storeId asc")
		List<OrdersVO> findAllByCustomerId( Integer CustomerId);
}


