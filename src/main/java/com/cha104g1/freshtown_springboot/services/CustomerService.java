package com.cha104g1.freshtown_springboot.services;

import com.cha104g1.freshtown_springboot.member.dto.dto.CustomerRegisterRequest;
import com.cha104g1.freshtown_springboot.model.Customer;

public interface CustomerService {

   Customer getCustomerById(Integer customerId);

    Integer register(CustomerRegisterRequest customerRegisterRequest);
}
