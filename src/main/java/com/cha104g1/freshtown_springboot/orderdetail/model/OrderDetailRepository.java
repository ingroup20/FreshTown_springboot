package com.cha104g1.freshtown_springboot.orderdetail.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;

public interface OrderDetailRepository extends JpaRepository<OrderDetailVO, Integer>{

}
