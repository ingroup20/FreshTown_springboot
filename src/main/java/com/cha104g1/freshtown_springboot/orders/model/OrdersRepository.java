package com.cha104g1.freshtown_springboot.orders.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface OrdersRepository extends JpaRepository<OrdersVO, Integer> {
    
    

}

