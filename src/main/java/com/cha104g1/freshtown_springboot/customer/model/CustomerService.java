package com.cha104g1.freshtown_springboot.customer.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("CustomerService")
public class CustomerService implements CustomerServiceIntf{
	
	@Autowired
	CustomerRepository repository;
	
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public CustomerVO addCustomerVO(CustomerVO customerVO) {
		repository.save(customerVO);
		return customerVO;
	}

	@Override
	public CustomerVO updateCustomerVO(CustomerVO customerVO) {
	    repository.save(customerVO);
	    return customerVO;
	}
    
	@Override
	public CustomerVO getCustomerVOById(Integer id) {
		Optional<CustomerVO> optional = repository.findById(id);
		return optional.orElse(null);
	}
    
	@Override
	public List<CustomerVO> getAllCustomerVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	@Override
	public List<CustomerVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CustomerVO> getCustomerVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
