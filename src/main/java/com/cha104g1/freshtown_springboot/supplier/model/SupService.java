package com.cha104g1.freshtown_springboot.supplier.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("supService")
public class SupService {
	
	@Autowired
	SupRepository repository;

	public void addSup(SupVO supVO) {
		repository.save(supVO);
	}
	
	public void updateSupVO(SupVO supVO) {
		repository.save(supVO);
	}
	
	public SupVO getOneSupplierName(String supplierName) {
		Optional<SupVO> optional = repository.findBySupplierName(supplierName);
		return optional.orElseGet(null);
	}
	
	public SupVO getOneSupplierContact(String supplierContact) {
		Optional<SupVO> optional = repository.findBySupplierContact(supplierContact);
		return optional.orElseGet(null);
	}
	
	public SupVO getOneSupplierStatus(Integer supplierState) {
		Optional<SupVO> optional = repository.findById(supplierState);
		return optional.orElseGet(null);
	}
	
	public SupVO getOneSup(Integer supId) {
		Optional<SupVO> optional = repository.findById(supId);
		return optional.orElse(null);
	}
	
	public List<SupVO> getAll(){
		return repository.findAll();
	}
}
