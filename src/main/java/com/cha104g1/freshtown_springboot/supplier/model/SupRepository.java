package com.cha104g1.freshtown_springboot.supplier.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupRepository extends JpaRepository<SupVO, Integer>{

    @Query("SELECT * FROM supplier sup WHERE sup.supplierName like %?1%")
    Optional<SupVO> findBySupplierName(String supplierName);
    
    @Query("SELECT * FROM supplier sup WHERE sup.supplierContact like %?1%")
    Optional<SupVO> findBySupplierContact(String supplierContact);
}
