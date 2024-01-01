package com.cha104g1.freshtown_springboot.refunds.model;

import java.util.List;
import java.util.Map;


public interface RefundsServiceIntf {

	RefundsVO addRefundsVO(RefundsVO refundsVO);
	
	RefundsVO updateRefundsVO(RefundsVO refundsVO);
	
	RefundsVO getRefundsVOById(Integer id);
	
	List<RefundsVO> getAllRefundsVOs(int currentPage);
	
	int getPageTotal();
	
	List<RefundsVO> getRefundsVOsByCompositeQuery(Map<String, String[]> map);

}
