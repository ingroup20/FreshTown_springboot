package com.cha104g1.freshtown_springboot.refunds.model;

import java.util.List;
import java.util.Map;


public interface RefundsServiceIntf {

	RefundsVO addRefundsVO(RefundsVO refundsVO);
	
	RefundsVO updateRefundsVO(RefundsVO refundsVO);
	
	RefundsVO getRefundsVOById(Integer id);
//	RefundsVO getRefundsVOByOrderId(Integer orderId);
//	RefundsVO getRefundsVOByOrderId(String refundState);
	
	List<RefundsVO> getAllRefundsVO(int currentPage);

	int getPageTotal();
	
	List<RefundsVO> getAll();
	List<RefundsVO> getAll(Map<String, String[]> map);

}
