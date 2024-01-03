package com.cha104g1.freshtown_springboot.member.dao.Impl;

import com.cha104g1.freshtown_springboot.member.dao.CustomerDao;
import com.cha104g1.freshtown_springboot.member.dto.dto.CustomerRegisterRequest;
import com.cha104g1.freshtown_springboot.model.Customer;
import com.cha104g1.freshtown_springboot.member.rowmapper.CustomerRowMapper;
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
        String sql = "SELECT customer_id, customerEmail, customerPw, created_date, last_modified_date FROM user WHERE customer_id = :customerId";

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
        String sql = "INSERT INTO customer(customerEmail, customerPw, created_date, last_modified_date) VALUS (:customerEmail, :customerPw, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", customerRegisterRequest.getCustomerEmail());
        map.put("password", customerRegisterRequest.getCustomerPw());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int customerId = keyHolder.getKey().intValue();

        return customerId;
    }
}
