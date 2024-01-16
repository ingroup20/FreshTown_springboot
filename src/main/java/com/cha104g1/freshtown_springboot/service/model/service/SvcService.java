package com.cha104g1.freshtown_springboot.service.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;
import com.cha104g1.freshtown_springboot.service.model.service.ServiceRepository;

@Service("svcService")
public class SvcService {
     
	@Autowired
	ServiceRepository repository;
	
	public void addService(ServiceVO serviceVO) {
		repository.save(serviceVO);
	}

	public void updateService(ServiceVO serviceVO) {
		repository.save(serviceVO);
	}
	
	public ServiceVO getOneService(Integer custSerNo) {
		Optional<ServiceVO> optional = repository.findById(custSerNo);
		return optional.orElse(null);
	}
	
	public List<ServiceVO> getAll() {
		return repository.findAll();
	}
	
	
	
}
