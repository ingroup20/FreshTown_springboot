package com.jasonlin.freshtown_springboot.dao;

import com.jasonlin.freshtown_springboot.dto.CustomerRegisterRequest;
import com.jasonlin.freshtown_springboot.model.Customer;

public interface CustomerDao {

    Customer getCustomerById(Integer customerId);
    Integer createCustomer(CustomerRegisterRequest customerRegisterRequest);
}
