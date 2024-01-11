package com.cha104g1.freshtown_springboot.customer.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerVO, Integer>{

}
