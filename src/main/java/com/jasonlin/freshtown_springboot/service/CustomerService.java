package com.jasonlin.freshtown_springboot.service;

import com.jasonlin.freshtown_springboot.dto.CustomerRegisterRequest;
import com.jasonlin.freshtown_springboot.model.Customer;

public interface CustomerService {


    Customer getCustomerById(Integer customerId);
    Integer register(CustomerRegisterRequest customerRegisterRequest);
}
