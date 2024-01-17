package com.jasonlin.freshtown_springboot.controller;

import com.jasonlin.freshtown_springboot.dto.CustomerRegisterRequest;
import com.jasonlin.freshtown_springboot.model.Customer;
import com.jasonlin.freshtown_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
@PostMapping("customers/register")
    public ResponseEntity<Customer> register(@RequestBody @Validated CustomerRegisterRequest customerRegisterRequest){

  Integer customerId = customerService.register(customerRegisterRequest);

  Customer customer = customerService.getCustomerById(customerId);

  return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
