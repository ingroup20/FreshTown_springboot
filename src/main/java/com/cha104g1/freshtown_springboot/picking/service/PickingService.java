package com.cha104g1.freshtown_springboot.picking.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.HibernateUtil_CompositeQuery_Material;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.picking.model.HibernateUtil_CompositeQuery_Picking;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;


@Service("pickingService")
public class PickingService {
	@Autowired
	PickingRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
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
	
	public List<PickingVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Picking.getAllC(map, sessionFactory.openSession());
	}
	

}
