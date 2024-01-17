package com.jasonlin.freshtown_springboot.dao.Impl;

import com.jasonlin.freshtown_springboot.dao.CustomerDao;
import com.jasonlin.freshtown_springboot.dto.CustomerRegisterRequest;
import com.jasonlin.freshtown_springboot.model.Customer;
import com.jasonlin.freshtown_springboot.rowmapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Customer getCustomerById(Integer customerId) {
            String sql = "SELECT customerId, customerPw, customerMob, mobChecked, customerEmail, customerNic, customerAddress, customerState FROM customer WHERE customerId = :customerId";

            Map<String, Object> map = new HashMap<>();
            map.put("customerId", customerId);

            List<Customer> customerList = namedParameterJdbcTemplate.query(sql, map, new CustomerRowMapper());


            if (customerList.size() > 0) {
                return customerList.get(0);
            } else {
                return null;
            }
    }

    @Override
    public Integer createCustomer(CustomerRegisterRequest customerRegisterRequest) {
        String sql = "INSERT INTO customer(customerPw, customerMob, mobChecked, customerEmail, customerNic, customerAddress, customerState) VALUES (:customerPw, :customerMob, :mobChecked, :customerEmail, :customerNic, :customerAddress, :customerState )";
        Map<String, Object> map = new HashMap<>();
        map.put("customerEmail", customerRegisterRequest.getCustomerEmail());
        map.put("customerPw", customerRegisterRequest.getCustomerPw());
        map.put("customerMob", customerRegisterRequest.getCustomerMob());
        map.put("mobChecked", customerRegisterRequest.getMobChecked());
        map.put("customerNic", customerRegisterRequest.getCustomerNic());
        map.put("customerAddress", customerRegisterRequest.getCustomerAddress());
        map.put("customerState", customerRegisterRequest.getCustomerState());




        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int customerId = keyHolder.getKey().intValue();

        return customerId;
    }
}
