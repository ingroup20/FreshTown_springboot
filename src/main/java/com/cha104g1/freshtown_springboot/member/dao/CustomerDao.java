package com.cha104g1.freshtown_springboot.member.dao;

import com.cha104g1.freshtown_springboot.member.dto.dto.CustomerRegisterRequest;
import com.cha104g1.freshtown_springboot.model.Customer;

public interface CustomerDao {

   Customer getCustomerById(Integer customerId);

    Integer createCustomer(CustomerRegisterRequest customerRegisterRequest);
}
