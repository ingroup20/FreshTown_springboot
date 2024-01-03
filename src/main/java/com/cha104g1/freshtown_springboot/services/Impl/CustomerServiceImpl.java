package com.cha104g1.freshtown_springboot.services.Impl;

import com.cha104g1.freshtown_springboot.member.dao.CustomerDao;
import com.cha104g1.freshtown_springboot.member.dto.dto.CustomerRegisterRequest;
import com.cha104g1.freshtown_springboot.model.Customer;
import com.cha104g1.freshtown_springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public Integer register(CustomerRegisterRequest customerRegisterRequest) {
        return customerDao.createCustomer(customerRegisterRequest);
    }
}
