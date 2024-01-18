package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupOrderRepository extends JpaRepository<SupOrderVO, Integer>{

    @Query("SELECT * FROM sup_order sup WHERE sup.purDate = ?1")
    Optional<SupOrderVO> findByPurDate(Date purDate);
    
    @Query("SELECT * FROM sup_order sup WHERE sup.preDate = ?1")
    Optional<SupOrderVO> findByPreDate(Date preDate);
    
    @Query("SELECT * FROM sup_order sup WHERE sup.deliDate = ?1")
    Optional<SupOrderVO> findByDeliDate(Date deliDate);
    
    @Query("SELECT * FROM sup_order sup WHERE sup.deliDate between ?1 and ?2")
    Optional<SupOrderVO> findByHybridPurDate(Date startpurDate, Date endpurDate);
    
    @Query("SELECT * FROM sup_order sup WHERE sup.deliDate between ?1 and ?2")
    Optional<SupOrderVO> findByHybridPreDate(Date startpreDate,Date endpreDate);
    
    @Query("SELECT * FROM sup_order sup WHERE sup.deliDate between ?1 and ?2")
    Optional<SupOrderVO> findByHybridDeliDate(Date startdeliDate,Date enddeliDate);
}
