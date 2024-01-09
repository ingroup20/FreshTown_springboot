package com.cha104g1.freshtown_springboot.refunds.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("refundsService")
public class RefundsService {

	@Autowired
	RefundsRepository repository;
		
	@Autowired
    private SessionFactory sessionFactory;
	
	
	public void addRefunds(RefundsVO refundsVO) {
		repository.save(refundsVO);
		System.out.println();
	}

	
	public void updateRefunds(RefundsVO refundsVO) {
		repository.save(refundsVO);
	}

	
	public RefundsVO getOneRefunds(Integer id) {
		Optional<RefundsVO> optional = repository.findById(id);
		return optional.orElse(null); 
	}

	
	public List<RefundsVO> getAll() {
		return repository.findAll();
	}
	

	public List<RefundsVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Refunds.getAllC(map,sessionFactory.openSession());
	}

	
	
}
