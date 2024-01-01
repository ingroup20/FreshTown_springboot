package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.compositeQuery.CompositeQueryStores;



@Service("storesService")
public class StoresService implements StoresServiceIntf{
	
	@Autowired
	StoresRepository repository;
	

	@Autowired
    private SessionFactory sessionFactory;//如何改寫


	@Override
	public StoresVO addStoresVO(StoresVO storesVO) {
		repository.save(storesVO);
		return storesVO;
	}

	@Override
	public StoresVO updateStoresVO(StoresVO storesVO) {
		repository.save(storesVO);
		return storesVO;
	}

	//單筆查詢
	@Override
	public StoresVO getStoresVOByStoreId(Integer storeId) {
		Optional<StoresVO> optional = repository.findById(storeId);
		return optional.orElse(null); 
	}
	
	@Override
	public StoresVO getStoresVOByStoreAccount(String storeAccount) {
		Optional<StoresVO> optional = repository.findByStoreAccount(storeAccount);
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	@Override
	public StoresVO getStoresVOByStoreEmail(String storeEmail) {
		Optional<StoresVO> optional = repository.findByStoreEmail(storeEmail);
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	@Override
	public StoresVO getStoresVOByStoreGPS(BigDecimal storeLat, BigDecimal storeLag) {
		Optional<StoresVO> optional = repository.findByStoreGPS(storeLat,storeLag);
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}
	
	
	
	@Override
	public List<StoresVO> getAllStoresVO(int currentPage) {
		return repository.findAll();
	}
	
	public List<StoresVO> getAllStoresVO() {
		return repository.findAll();
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StoresVO> getStoresVOByCompositeQuery(Map<String, String[]> map) {
		return CompositeQueryStores.getAllC(map,sessionFactory.openSession());
	}


	
	

}
