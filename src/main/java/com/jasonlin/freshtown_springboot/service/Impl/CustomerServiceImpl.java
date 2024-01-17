package com.jasonlin.freshtown_springboot.service.Impl;

import com.jasonlin.freshtown_springboot.dao.CustomerDao;
import com.jasonlin.freshtown_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
}
