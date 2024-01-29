package com.cha104g1.freshtown_springboot.customizedorder.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CustomizedOrderService")
public class CustomizedOrderService {

	@Autowired
	CustomizedOrderRepository repository;
	
	
	public CustomizedOrderService(CustomizedOrderRepository repository) {
		this.repository=repository;
	}
	
	
	public CustomizedOrderVO addCustomizedOrder(CustomizedOrderVO CustomizedOrderVO) {
		repository.save(CustomizedOrderVO);
		return CustomizedOrderVO;
	}

	
	public CustomizedOrderVO updateCustomizedOrder(CustomizedOrderVO CustomizedOrderVO) {
		repository.save(CustomizedOrderVO);
		return CustomizedOrderVO;
	}

	
	public CustomizedOrderVO getOneCustomizedOrder(Integer custedOrderNo) {
		Optional<CustomizedOrderVO> optional = repository.findById(custedOrderNo);
		return optional.orElse(null);
	}
	

	public List<CustomizedOrderVO> getAll() {
		return repository.findAll();
	}



}
