package com.cha104g1.freshtown_springboot.picking.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.picking.model.model.PickingVO;


@Service("pickingService")
public class PickingService {
	@Autowired
	PickingRepository repository;
	
	public void addPicking(PickingVO pickingVO) {
		repository.save(pickingVO);
	}
	
	public void updatePicking(PickingVO pickingVO) {
		repository.save(pickingVO);
	}
	
	public PickingVO getOnePicking(Integer pickingNo) {
		Optional<PickingVO> optional = repository.findById(pickingNo);
		return optional.orElse(null);
	}
	public List<PickingVO> getAll() {
		return repository.findAll();
	}
	
	
}
