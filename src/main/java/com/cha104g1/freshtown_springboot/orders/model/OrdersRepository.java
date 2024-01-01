package com.cha104g1.freshtown_springboot.orders.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface OrdersRepository extends JpaRepository<OrdersVO, Long>, JpaSpecificationExecutor<OrdersVO> {
    
    // 这里定义一些自定义的查询方法（如果需要）

}

