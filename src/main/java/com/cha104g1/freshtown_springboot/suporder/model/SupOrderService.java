package com.cha104g1.freshtown_springboot.suporder.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	
	public SupOrderVO getOneSupId(Integer supId) {
		Optional<SupOrderVO> optional = repository.findById(supId);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getOnePurNo(Integer purNo) {
		Optional<SupOrderVO> optional = repository.findById(purNo);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getOneOStatus(Integer oStatus) {
		Optional<SupOrderVO> optional = repository.findById(oStatus);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getOnePurDate(Date purDate) {
		Optional<SupOrderVO> optional = repository.findByPurDate(purDate);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getOnePreDate(Date preDate) {
		Optional<SupOrderVO> optional = repository.findByPreDate(preDate);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getOneDeliDate(Date deliDate) {
		Optional<SupOrderVO> optional = repository.findByDeliDate(deliDate);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getByHybridPurDate(Date startpurDate, Date endpurDate) {
		Optional<SupOrderVO> optional = repository.findByHybridPurDate(startpurDate, endpurDate);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getByHybridPreDate(Date startpreDate,Date endpreDate) {
		Optional<SupOrderVO> optional = repository.findByHybridPreDate(startpreDate, endpreDate);
		return optional.orElseGet(null);
	}
	
	public SupOrderVO getByHybridDeliDate(Date startdeliDate,Date enddeliDate) {
		Optional<SupOrderVO> optional = repository.findByHybridDeliDate(startdeliDate, enddeliDate);
		return optional.orElseGet(null);
	}
	
	public List<SupOrderVO> getAll() {
		return repository.findAll();
	}

	public List<SupOrderVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_SupOrder.getAllC(map,sessionFactory.openSession());
	}
}
