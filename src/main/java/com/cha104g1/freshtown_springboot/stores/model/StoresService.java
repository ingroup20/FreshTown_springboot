package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.refunds.model.HibernateUtil_CompositeQuery_Refunds;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

@Service("storesService")
public class StoresService {
	
	@Autowired
	StoresRepository repository;
	

	@Autowired
    private SessionFactory sessionFactory;//如何改寫



	public StoresVO addStores(StoresVO storesVO) {
		repository.save(storesVO);
		return storesVO;
	}

	
	public StoresVO updateStores(StoresVO storesVO) {
		repository.save(storesVO);
		return storesVO;
	}

	//單筆查詢
	public StoresVO getOneStores(Integer storeId) {
		Optional<StoresVO> optional = repository.findById(storeId);
		return optional.orElse(null); 
	}
	

	public List<StoresVO> getAll() {
		return repository.findAll();
	}

	public List<StoresVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Stores.getAllC(map,sessionFactory.openSession());
	}

	

}
