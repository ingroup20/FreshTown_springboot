package com.cha104g1.freshtown_springboot.orderdetail.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeRepository;

@Service("OrderDetailService")
public class OrderDetailService {
	
	@Autowired
	OrderDetailRepository  repository;
	
	
	public OrderDetailService(OrderDetailRepository repository) {
		this.repository=repository;
	}
	
	public OrderDetailVO addOrderDetail(OrderDetailVO orderDetailVO) {
		repository.save(orderDetailVO);
		return orderDetailVO;
	}

	
	public OrderDetailVO updateOrderDetail(OrderDetailVO orderDetailVO) {
		repository.save(orderDetailVO);
		return orderDetailVO;
	}

	
	public OrderDetailVO getOneOrderDetail(Integer orderDtlNo) {
		Optional<OrderDetailVO> optional = repository.findById(orderDtlNo);
		return optional.orElse(null);
	}
	

	public List<OrderDetailVO> getAll() {
		return repository.findAll();
	}
}
