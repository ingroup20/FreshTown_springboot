package com.cha104g1.freshtown_springboot.supplier.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupRepository extends JpaRepository<SupVO, Integer>{

//    Optional<SupVO> findBySupplierName(String supplierName);
//    
//    Optional<SupVO> findBySupplierContact(String supplierContact);
    
    @Query("SELECT sup FROM SupVO sup WHERE sup.supplierName like %:supplierName% OR sup.supplierContact LIKE %:supplierContact% OR sup.supplierState = :supplierState")
    List<SupVO> findSupplierByCompositeQuery(@Param("supplierName") String supplierName, @Param("supplierContact") String supplierContact, @Param("supplierState") Integer supplierState);
}
