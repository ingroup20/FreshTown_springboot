package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;

public interface SupOrderServiceIntf {
	
	SupOrderVO addSupOrder(SupOrderVO suporderVO);
	SupOrderVO updateSupOrderVO(SupOrderVO suporderVO);
    SupOrderVO getOneSupOrder(Integer id);
	List<SupOrderVO> getAll();

}
