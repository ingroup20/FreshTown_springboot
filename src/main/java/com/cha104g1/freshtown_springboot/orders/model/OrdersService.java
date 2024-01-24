package com.cha104g1.freshtown_springboot.orders.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.refunds.model.HibernateUtil_CompositeQuery_Refunds;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Predicate;

@Service("ordersService")
public class OrdersService {

    @Autowired
    private OrdersRepository repository;
    
    @Autowired
    private SessionFactory sessionFactory;


	public OrdersVO addOrders(OrdersVO ordersVO) {
		return repository.save(ordersVO);
	}


	public OrdersVO updateOrders(OrdersVO ordersVO) {
		return repository.save(ordersVO);
	}


	public OrdersVO getOneOrders(Integer orderId) {
		Optional<OrdersVO> optional = repository.findById(orderId);
		return optional.orElse(null);
	}


	public List<OrdersVO> getAll() {
		return repository.findAll();
	}

	public List<OrdersVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Orders.getAllC(map,sessionFactory.openSession());
	}

	public List<OrdersVO> getAllByStore(Integer storeId) {
		return repository.findAllByStoreId(storeId);
	}
	
	public List<OrdersVO> getAllByCustomer(Integer CustomerId) {
		return repository.findAllByCustomerId(CustomerId);
	}
    
}