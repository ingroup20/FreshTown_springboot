package com.cha104g1.freshtown_springboot.orderdetail.model;

import java.util.List;
import java.util.Map;

public interface OrderDetailServiceIntf {
	
	OrderDetailVO addOrderDetailVO(OrderDetailVO orderDetailVO);
	
	OrderDetailVO updateOrderDetailVO(OrderDetailVO orderDetailVO);
	
	OrderDetailVO getOrderDetailVOByOrderDtlNo(Integer orderDtlNo);
	
	List<OrderDetailVO> getAllOrderDetailVOs(int currentPage);
	
	int getPageTotal();
	
	List<OrderDetailVO> getOrderDetailVOsByCompositeQuery(Map<String, String[]> map);

}
