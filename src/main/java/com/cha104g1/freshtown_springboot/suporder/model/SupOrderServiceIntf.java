package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;

public interface SupOrderServiceIntf {
	
	SupOrderVO addSupOrder(SupOrderVO suporderVO);
	SupOrderVO updateSupOrderVO(SupOrderVO suporderVO);
    SupOrderVO getOneSupOrder(Integer id);
	SupOrderVO getOneSupId(Integer supId);
	SupOrderVO getOnePurNo(Integer purNo);
	SupOrderVO getOneOStatus(Integer oStatus);
	SupOrderVO getOnePurDate(Date purDate);
	SupOrderVO getOnePreDate(Date preDate);
	SupOrderVO getOneDeliDate(Date deliDate);
	SupOrderVO getByHybridPurDate(Date startpurDate, Date endpurDate);
	SupOrderVO getByHybridPreDate(Date startpreDate,Date endpreDate);
	SupOrderVO getByHybridDeliDate(Date startdeliDate,Date enddeliDate);
	List<SupOrderVO> getAll();

}
