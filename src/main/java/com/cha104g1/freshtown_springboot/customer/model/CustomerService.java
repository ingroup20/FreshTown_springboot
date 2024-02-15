package com.cha104g1.freshtown_springboot.customer.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






@Service("CustomerService")
public class CustomerService {
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	
	public CustomerVO addCustomer(CustomerVO customerVO) {
		repository.save(customerVO);
		return customerVO;
	}

	
	public CustomerVO updateCustomerVO(CustomerVO customerVO) {
	    repository.save(customerVO);
	    return customerVO;
	}
    

	public List<CustomerVO> getAll() {
		return repository.findAll();
	}
	

	public List<CustomerVO> getCustomerVOsByCompositeQuery(Map<String, String[]> map) {
		return null;
	}
	
	//取得SQL身分帳密(中群)
	public CustomerVO getByCustomerAccount(String customerAccount) {
		CustomerVO  customerLogin =repository.findByCustomerAccount(customerAccount);
		return customerLogin; 
	}

	public CustomerVO getOneCustomer(Integer customerId) {
		Optional<CustomerVO> optional = repository.findById(customerId);
		return optional.orElse(null); 
	}
	
	public List<CustomerVO> getAllCustomerVO(int currentPage) {
		return repository.findAll();
	}
	
	public List<CustomerVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Customer.getAllC(map, sessionFactory.openSession());
	}
	


}
