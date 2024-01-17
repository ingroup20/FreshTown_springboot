package com.jasonlin.freshtown_springboot.service.Impl;

import com.jasonlin.freshtown_springboot.dao.CustomerDao;
import com.jasonlin.freshtown_springboot.dto.CustomerRegisterRequest;
import com.jasonlin.freshtown_springboot.model.Customer;
import com.jasonlin.freshtown_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
