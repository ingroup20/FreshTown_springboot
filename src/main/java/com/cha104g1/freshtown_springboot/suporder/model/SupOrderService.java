package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;



@Service("supOrderService")
public class SupOrderService {
	
	@Autowired
	SupOrderRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addSupOrder(SupOrderVO supOrderVO) {
		repository.save(supOrderVO);
	}
	
	public void updateSupOrderVO(SupOrderVO supOrderVO) {
		repository.save(supOrderVO);
	}
	
	public SupOrderVO getOneSupOrder(Integer id) {
		Optional<SupOrderVO> optional = repository.findById(id);
		return optional.orElseGet(null);
	}
	
	public List<SupOrderVO> findSupOrderByCompositeQuery(Integer id, Integer supId, String purNo, Integer amount, Integer unitPrice, Date purDate, Date preDate, Integer oStatus, Date deliDate, String marks) {
        return repository.findSupOrderByCompositeQuery(id, supId, purNo, amount, unitPrice, purDate, preDate, oStatus, deliDate, marks);
	}
	
	public List<SupOrderVO> getAll() {
		return repository.findAll();
	}

	public List<SupOrderVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_SupOrder.getAllC(map,sessionFactory.openSession());
	}
	
	public MaterialVO getMaterial(Integer purNo) {
		return repository.findMaterial(purNo);
	}
	
	public MaterialVO getStock(Integer purNo) {
		return repository.findStock(purNo);
	}
	
	public MaterialVO getQuantity(Integer purNo) {
		return repository.findQuantity(purNo);
	}
}
