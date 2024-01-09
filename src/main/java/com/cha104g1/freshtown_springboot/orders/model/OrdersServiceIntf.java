package com.cha104g1.freshtown_springboot.orders.model;

import java.util.List;
import java.util.Map;


public interface OrdersServiceIntf {
	
	OrdersVO addOrdersVO(OrdersVO OrdersVO);
	
	OrdersVO updateOrdersVO(OrdersVO OrdersVO);
	
	OrdersVO getOrdersVOByOrderId(Integer orderId);
	
	List<OrdersVO> getAllOrdersVO(int currentPage);
	List<OrdersVO> getAll();
	
	int getPageTotal();
	
	List<OrdersVO> getAll(Map<String, String[]> map);

}
