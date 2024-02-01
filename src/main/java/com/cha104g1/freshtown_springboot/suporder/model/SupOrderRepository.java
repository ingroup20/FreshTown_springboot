package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;


public interface SupOrderRepository extends JpaRepository<SupOrderVO, Integer>{

    @Query("SELECT suporder FROM SupOrderVO suporder WHERE suporder.id like %:id% OR suporder.supVO.supId = :supId OR suporder.materialVO.itemNumber = :purNo OR suporder.amount = :amount OR suporder.unitPrice = :unitPrice OR suporder.purDate = :purDate OR suporder.preDate = :preDate OR suporder.oStatus = :oStatus OR suporder.deliDate = :deliDate OR suporder.marks LIKE %:marks%")
    List<SupOrderVO> findSupOrderByCompositeQuery(@Param("id") Integer id, @Param("supId") Integer supId, @Param("purNo") String purNo, @Param("amount") Integer amount, @Param("unitPrice") Integer unitPrice, @Param("purDate") Date purDate, @Param("preDate") Date preDate, @Param("oStatus") Integer oStatus, @Param("deliDate") Date deliDate, @Param("marks") String marks);
    
    @Query("select m from MaterialVO m where m.itemNumber = :purNo")
    MaterialVO findMaterial(@Param("purNo") Integer purNo);
    
    @Query("select m.stockQuantity from MaterialVO m where m.itemNumber = :purNo")
    MaterialVO findStock(@Param("purNo") Integer purNo);
    
    @Query("select m.quantityNot from MaterialVO m where m.itemNumber = :purNo")
    MaterialVO findQuantity(@Param("purNo") Integer purNo);
}
