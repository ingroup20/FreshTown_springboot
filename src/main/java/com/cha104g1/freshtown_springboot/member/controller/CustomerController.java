package com.cha104g1.freshtown_springboot.member.controller;

import com.cha104g1.freshtown_springboot.member.dto.dto.CustomerRegisterRequest;
import com.cha104g1.freshtown_springboot.model.Customer;
import com.cha104g1.freshtown_springboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers/register")
    public ResponseEntity<Customer> register(@RequestBody @Valid CustomerRegisterRequest customerRegisterRequest) {
       Integer customerId = customerService.register(customerRegisterRequest);

       Customer customer = customerService.getCustomerById(customerId);

       return ResponseEntity.status(HttpStatus.CREATED).body(customer);

    }
}
