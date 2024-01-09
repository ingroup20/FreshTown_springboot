package com.cha104g1.freshtown_springboot.refunds.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RefundsRepository extends JpaRepository<RefundsVO, Integer> {

	
}