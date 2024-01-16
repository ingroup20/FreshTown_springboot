package com.cha104g1.freshtown_springboot.supplier.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.stores.model.HibernateUtil_CompositeQuery_Stores;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Service("supService")
public class SupService {
	
	@Autowired
	SupRepository repository;
	
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addSup(SupVO supVO) {
		repository.save(supVO);
	}
	
	public void updateSupVO(SupVO supVO) {
		repository.save(supVO);
	}
	
	public SupVO getOneSup(Integer supId) {
		Optional<SupVO> optional = repository.findById(supId);
		return optional.orElse(null);
	}
	
    public List<SupVO> listSupByCompositeQuery(String supplierName, String supplierContact, Integer supplierState) {
        return repository.findSupplierByCompositeQuery(supplierName, supplierContact, supplierState);
    }
    
	public List<SupVO> getAll(){
		return repository.findAll();
	}
	
	public List<SupVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Supplier.getAllC(map,sessionFactory.openSession());
	}
}
