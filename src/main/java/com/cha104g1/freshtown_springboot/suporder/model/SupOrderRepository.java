package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SupOrderRepository extends JpaRepository<SupOrderVO, Integer>{

    @Query("SELECT suporder FROM SupOrderVO suporder WHERE suporder.id like %:id% OR suporder.supId LIKE %:supId% OR suporder.purNo LIKE %:purNo% OR suporder.amount LIKE %:amount% OR suporder.unitPrice LIKE %:unitPrice% OR suporder.purDate LIKE %:purDate% OR suporder.preDate LIKE %:preDate% OR suporder.oStatus = :oStatus OR suporder.deliDate LIKE %:deliDate% OR suporder.marks LIKE %:marks%")
    List<SupOrderVO> findSupOrderByCompositeQuery(@Param("id") Integer id, @Param("supId") Integer supId, @Param("purNo") Integer purNo, @Param("amount") Integer amount, @Param("unitPrice") Integer unitPrice, @Param("purDate") Date purDate, @Param("preDate") Date preDate, @Param("oStatus") Integer oStatus, @Param("deliDate") Date deliDate, @Param("marks") String marks);
}
